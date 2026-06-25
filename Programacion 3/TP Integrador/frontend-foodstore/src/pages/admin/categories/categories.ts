import { exigirSesion } from '../../../utils/auth';
import { fetchCategorias } from '../../../utils/api';
import { renderAdminLayout } from '../../../utils/layout';
import { actualizarEnOverlay, combinarOverlay, crearEnOverlay } from '../../../utils/localOverlay';
import type { Categoria } from '../../../types/categoria';

const sesion = exigirSesion(['ADMIN']);
if (sesion) {
  renderAdminLayout(document.getElementById('header')!, sesion, 'categories');
  void init();
}

async function init(): Promise<void> {
  const base = await fetchCategorias();
  render(base);
}

function render(base: Categoria[]): void {
  const categorias = combinarOverlay('categorias', base).filter(c => !c.eliminado);
  const content = document.getElementById('admin-content')!;

  content.innerHTML = `
    <div class="admin-toolbar">
      <h1>Gestión de Categorías</h1>
      <button id="btn-new" class="btn">+ Nueva Categoría</button>
    </div>
    <div class="table-wrap">
      <table>
        <thead><tr><th>ID</th><th>Imagen</th><th>Nombre</th><th>Descripción</th><th>Acciones</th></tr></thead>
        <tbody>
          ${categorias.map(c => `
            <tr data-id="${c.id}">
              <td class="col-id">${c.id}</td>
              <td>${c.imagen ? `<img class="thumb" src="${c.imagen}" alt="${c.nombre}" />` : '—'}</td>
              <td>${c.nombre}</td>
              <td class="col-desc" title="${c.descripcion}">${c.descripcion}</td>
              <td class="col-actions">
                <button class="btn btn-sm btn-edit">Editar</button>
                <button class="btn btn-danger btn-sm btn-delete">Eliminar</button>
              </td>
            </tr>
          `).join('') || `<tr><td colspan="5">No hay categorías activas.</td></tr>`}
        </tbody>
      </table>
    </div>
  `;

  document.getElementById('btn-new')!.addEventListener('click', () => abrirModal(base));

  content.querySelectorAll<HTMLElement>('tr[data-id]').forEach(row => {
    const id = Number(row.dataset.id);
    const categoria = categorias.find(c => c.id === id)!;
    row.querySelector('.btn-edit')!.addEventListener('click', () => abrirModal(base, categoria));
    row.querySelector('.btn-delete')!.addEventListener('click', () => {
      if (confirm(`¿Dar de baja la categoría "${categoria.nombre}"?`)) {
        actualizarEnOverlay<Categoria>('categorias', id, { eliminado: true });
        render(base);
      }
    });
  });
}

function abrirModal(base: Categoria[], categoria?: Categoria): void {
  const root = document.getElementById('modal-root')!;
  root.innerHTML = `
    <div class="modal-overlay">
      <div class="modal-box">
        <button class="modal-close" id="modal-close">✕</button>
        <h3>${categoria ? 'Editar Categoría' : 'Nueva Categoría'}</h3>
        <div id="modal-error"></div>
        <form id="cat-form">
          <div class="field">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" value="${categoria?.nombre ?? ''}" required />
          </div>
          <div class="field">
            <label for="descripcion">Descripción</label>
            <input id="descripcion" type="text" value="${categoria?.descripcion ?? ''}" required />
          </div>
          <div class="field">
            <label for="imagen">URL de Imagen</label>
            <input id="imagen" type="text" placeholder="https://ejemplo.com/imagen.jpg" value="${categoria?.imagen ?? ''}" required />
          </div>
          <button type="submit" class="btn">Guardar</button>
        </form>
      </div>
    </div>
  `;
  document.getElementById('modal-close')!.addEventListener('click', () => { root.innerHTML = ''; });

  document.getElementById('cat-form')!.addEventListener('submit', (e) => {
    e.preventDefault();
    const nombre = (document.getElementById('nombre') as HTMLInputElement).value.trim();
    const descripcion = (document.getElementById('descripcion') as HTMLInputElement).value.trim();
    const imagen = (document.getElementById('imagen') as HTMLInputElement).value.trim();

    if (!nombre || !descripcion || !imagen) {
      document.getElementById('modal-error')!.innerHTML = `<div class="form-error">Completá todos los campos.</div>`;
      return;
    }

    if (categoria) {
      actualizarEnOverlay<Categoria>('categorias', categoria.id, { nombre, descripcion, imagen });
    } else {
      crearEnOverlay<Categoria>('categorias', { nombre, descripcion, imagen, eliminado: false }, base.map(c => c.id));
    }
    root.innerHTML = '';
    render(base);
  });
}
