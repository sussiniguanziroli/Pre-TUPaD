import { exigirSesion } from '../../../utils/auth';
import { fetchCategorias, fetchProductos } from '../../../utils/api';
import { renderStoreHeader } from '../../../utils/layout';
import { combinarOverlay } from '../../../utils/localOverlay';
import type { Producto } from '../../../types/producto';

const sesion = exigirSesion();
if (sesion) {
  renderStoreHeader(document.getElementById('header')!, sesion);
  void init();
}

async function init(): Promise<void> {
  const [categoriasBase, productosBase] = await Promise.all([fetchCategorias(), fetchProductos()]);
  const categorias = combinarOverlay('categorias', categoriasBase);
  const productos = combinarOverlay('productos', productosBase);

  const categoriasActivas = categorias.filter(c => !c.eliminado);
  const disponibles = productos.filter(p => p.disponible && !p.eliminado);

  let categoriaSeleccionada: number | 'todas' = 'todas';
  let busqueda = '';
  let orden = '';

  renderCategoryBar();
  renderGrid();

  function renderCategoryBar(): void {
    const bar = document.getElementById('category-bar')!;
    bar.innerHTML = '';

    const allPill = document.createElement('button');
    allPill.className = 'cat-pill active';
    allPill.textContent = 'Todos los productos';
    allPill.addEventListener('click', () => {
      setActivePill(allPill);
      categoriaSeleccionada = 'todas';
      renderGrid();
    });
    bar.appendChild(allPill);

    const divider = document.createElement('div');
    divider.className = 'cat-divider';
    bar.appendChild(divider);

    categoriasActivas.forEach(c => {
      const pill = document.createElement('button');
      pill.className = 'cat-pill';
      pill.textContent = c.nombre;
      pill.addEventListener('click', () => {
        setActivePill(pill);
        categoriaSeleccionada = c.id;
        renderGrid();
      });
      bar.appendChild(pill);
    });
  }

  function setActivePill(activo: HTMLButtonElement): void {
    document.querySelectorAll('.cat-pill').forEach(p => p.classList.remove('active'));
    activo.classList.add('active');
  }

  document.getElementById('search-input')!.addEventListener('input', (e) => {
    busqueda = (e.target as HTMLInputElement).value.toLowerCase();
    renderGrid();
  });

  document.getElementById('sort-select')!.addEventListener('change', (e) => {
    orden = (e.target as HTMLSelectElement).value;
    renderGrid();
  });

  function renderGrid(): void {
    let lista = disponibles.filter(p =>
      (categoriaSeleccionada === 'todas' || p.categoriaId === categoriaSeleccionada) &&
      p.nombre.toLowerCase().includes(busqueda)
    );

    if (orden === 'nombre-asc') lista = [...lista].sort((a, b) => a.nombre.localeCompare(b.nombre));
    if (orden === 'nombre-desc') lista = [...lista].sort((a, b) => b.nombre.localeCompare(a.nombre));
    if (orden === 'precio-asc') lista = [...lista].sort((a, b) => a.precio - b.precio);
    if (orden === 'precio-desc') lista = [...lista].sort((a, b) => b.precio - a.precio);

    const heading = categoriaSeleccionada === 'todas'
      ? 'Todos los Productos'
      : categoriasActivas.find(c => c.id === categoriaSeleccionada)?.nombre ?? 'Productos';
    document.getElementById('catalog-heading')!.textContent = heading;
    document.getElementById('catalog-count')!.textContent = `${lista.length} producto${lista.length === 1 ? '' : 's'}`;

    renderProducts(lista);
  }
}

function renderProducts(productos: Producto[]): void {
  const container = document.getElementById('product-container')!;
  container.innerHTML = '';

  if (productos.length === 0) {
    container.innerHTML = '<div class="empty-state">No se encontraron productos.</div>';
    return;
  }

  productos.forEach(p => {
    const div = document.createElement('div');
    div.className = 'glass-card';
    div.innerHTML = `
      <img src="${p.imagen}" alt="${p.nombre}" loading="lazy" />
      <div class="card-body">
        <h3>${p.nombre}</h3>
        <p class="desc">${p.descripcion}</p>
        <div class="price-row">
          <p class="price">$${p.precio.toFixed(2)}</p>
          <span class="tag-pill ok">Disponible</span>
        </div>
        <button data-id="${p.id}" class="btn-add">Ver Producto</button>
      </div>
    `;
    container.appendChild(div);
  });

  container.querySelectorAll<HTMLButtonElement>('.btn-add').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.stopPropagation();
      location.href = `../productDetail/productDetail.html?id=${btn.dataset.id}`;
    });
  });
  container.querySelectorAll<HTMLElement>('.glass-card').forEach(card => {
    card.addEventListener('click', () => {
      const id = card.querySelector('button')!.dataset.id;
      location.href = `../productDetail/productDetail.html?id=${id}`;
    });
  });
}
