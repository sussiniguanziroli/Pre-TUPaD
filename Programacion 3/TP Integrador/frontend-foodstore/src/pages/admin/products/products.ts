import { exigirSesion } from '../../../utils/auth';
import { fetchCategorias, fetchProductos } from '../../../utils/api';
import { renderAdminLayout } from '../../../utils/layout';
import { actualizarEnOverlay, combinarOverlay, crearEnOverlay } from '../../../utils/localOverlay';
import type { Categoria } from '../../../types/categoria';
import type { Producto } from '../../../types/producto';

const sesion = exigirSesion(['ADMIN']);
if (sesion) {
  renderAdminLayout(document.getElementById('header')!, sesion, 'products');
  void init();
}

async function init(): Promise<void> {
  const [productosBase, categoriasBase] = await Promise.all([fetchProductos(), fetchCategorias()]);
  render(productosBase, categoriasBase);
}

function render(productosBase: Producto[], categoriasBase: Categoria[]): void {
  const productos = combinarOverlay('productos', productosBase).filter(p => !p.eliminado);
  const categorias = combinarOverlay('categorias', categoriasBase).filter(c => !c.eliminado);
  const content = document.getElementById('admin-content')!;

  content.innerHTML = `
    <div class="admin-toolbar">
      <h1>Gestión de Productos</h1>
      <button id="btn-new" class="btn">+ Nuevo Producto</button>
    </div>
    <div class="table-wrap">
      <table>
        <thead><tr><th>ID</th><th>Imagen</th><th>Nombre</th><th>Descripción</th><th>Precio</th><th>Categoría</th><th>Stock</th><th>Estado</th><th>Acciones</th></tr></thead>
        <tbody>
          ${productos.map(p => {
            const catNombre = categorias.find(c => c.id === p.categoriaId)?.nombre ?? '—';
            return `
              <tr data-id="${p.id}">
                <td class="col-id">${p.id}</td>
                <td><img class="thumb" src="${p.imagen}" alt="${p.nombre}" /></td>
                <td>${p.nombre}</td>
                <td class="col-desc" title="${p.descripcion}">${p.descripcion}</td>
                <td class="col-price">$${p.precio.toFixed(2)}</td>
                <td>${catNombre}</td>
                <td class="col-stock">${p.stock}</td>
                <td class="col-status"><span class="tag-pill ${p.disponible ? 'ok' : 'no'}">${p.disponible ? 'Disponible' : 'No disponible'}</span></td>
                <td class="col-actions">
                  <button class="btn btn-sm btn-edit">Editar</button>
                  <button class="btn btn-danger btn-sm btn-delete">Eliminar</button>
                </td>
              </tr>
            `;
          }).join('') || `<tr><td colspan="9">No hay productos activos.</td></tr>`}
        </tbody>
      </table>
    </div>
  `;

  document.getElementById('btn-new')!.addEventListener('click', () => abrirModal(productosBase, categorias));

  content.querySelectorAll<HTMLElement>('tr[data-id]').forEach(row => {
    const id = Number(row.dataset.id);
    const producto = productos.find(p => p.id === id)!;
    row.querySelector('.btn-edit')!.addEventListener('click', () => abrirModal(productosBase, categorias, producto));
    row.querySelector('.btn-delete')!.addEventListener('click', () => {
      if (confirm(`¿Dar de baja el producto "${producto.nombre}"?`)) {
        actualizarEnOverlay<Producto>('productos', id, { eliminado: true });
        render(productosBase, categoriasBase);
      }
    });
  });
}

function abrirModal(productosBase: Producto[], categorias: Categoria[], producto?: Producto): void {
  const root = document.getElementById('modal-root')!;
  root.innerHTML = `
    <div class="modal-overlay">
      <div class="modal-box">
        <button class="modal-close" id="modal-close">✕</button>
        <h3>${producto ? 'Editar Producto' : 'Nuevo Producto'}</h3>
        <div id="modal-error"></div>
        <form id="prod-form">
          <div class="field">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" value="${producto?.nombre ?? ''}" required />
          </div>
          <div class="field">
            <label for="descripcion">Descripción</label>
            <input id="descripcion" type="text" value="${producto?.descripcion ?? ''}" required />
          </div>
          <div class="field">
            <label for="precio">Precio</label>
            <input id="precio" type="number" step="0.01" min="0.01" value="${producto?.precio ?? ''}" required />
          </div>
          <div class="field">
            <label for="stock">Stock</label>
            <input id="stock" type="number" step="1" min="0" value="${producto?.stock ?? ''}" required />
          </div>
          <div class="field">
            <label for="categoria">Categoría</label>
            <select id="categoria" required>
              ${categorias.map(c => `<option value="${c.id}" ${producto?.categoriaId === c.id ? 'selected' : ''}>${c.nombre}</option>`).join('')}
            </select>
          </div>
          <div class="field">
            <label for="imagen">URL de Imagen</label>
            <input id="imagen" type="text" placeholder="https://ejemplo.com/imagen.jpg" value="${producto?.imagen ?? ''}" />
          </div>
          <div class="field checkbox-field">
            <input id="disponible" type="checkbox" ${producto?.disponible !== false ? 'checked' : ''} />
            <label for="disponible">Producto disponible</label>
          </div>
          <button type="submit" class="btn">Guardar</button>
        </form>
      </div>
    </div>
  `;
  document.getElementById('modal-close')!.addEventListener('click', () => { root.innerHTML = ''; });

  document.getElementById('prod-form')!.addEventListener('submit', (e) => {
    e.preventDefault();
    const nombre = (document.getElementById('nombre') as HTMLInputElement).value.trim();
    const descripcion = (document.getElementById('descripcion') as HTMLInputElement).value.trim();
    const precio = Number((document.getElementById('precio') as HTMLInputElement).value);
    const stock = Number((document.getElementById('stock') as HTMLInputElement).value);
    const categoriaId = Number((document.getElementById('categoria') as HTMLSelectElement).value);
    const imagen = (document.getElementById('imagen') as HTMLInputElement).value.trim();
    const disponible = (document.getElementById('disponible') as HTMLInputElement).checked;
    const errorBox = document.getElementById('modal-error')!;

    if (!nombre || !descripcion || !categoriaId) {
      errorBox.innerHTML = `<div class="form-error">Completá todos los campos obligatorios.</div>`;
      return;
    }
    if (!(precio > 0)) {
      errorBox.innerHTML = `<div class="form-error">El precio debe ser mayor a 0.</div>`;
      return;
    }
    if (!(stock >= 0)) {
      errorBox.innerHTML = `<div class="form-error">El stock no puede ser negativo.</div>`;
      return;
    }

    if (producto) {
      actualizarEnOverlay<Producto>('productos', producto.id, { nombre, descripcion, precio, stock, categoriaId, imagen, disponible });
    } else {
      crearEnOverlay<Producto>(
        'productos',
        { nombre, descripcion, precio, stock, categoriaId, imagen, disponible, eliminado: false },
        productosBase.map(p => p.id)
      );
    }
    root.innerHTML = '';
    render(productosBase, categorias);
  });
}
