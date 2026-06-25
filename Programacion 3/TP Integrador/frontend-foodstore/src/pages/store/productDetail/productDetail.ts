import { exigirSesion } from '../../../utils/auth';
import { fetchProductos } from '../../../utils/api';
import { renderStoreHeader, updateCartBadge } from '../../../utils/layout';
import { addToCart } from '../../../utils/cart';
import { combinarOverlay } from '../../../utils/localOverlay';

const sesion = exigirSesion();
if (sesion) {
  renderStoreHeader(document.getElementById('header')!, sesion);
  void init();
}

async function init(): Promise<void> {
  const content = document.getElementById('content')!;
  const id = Number(new URLSearchParams(location.search).get('id'));
  const productosBase = await fetchProductos();
  const productos = combinarOverlay('productos', productosBase);
  const producto = productos.find(p => p.id === id && !p.eliminado);

  if (!producto) {
    content.innerHTML = `<div class="empty-state">No se encontró el producto solicitado.
      <br /><a href="../home/home.html" class="btn">Volver al catálogo</a></div>`;
    return;
  }

  let cantidad = 1;
  const sinStock = producto.stock <= 0;
  const noDisponible = !producto.disponible || sinStock;

  content.innerHTML = `
    <div class="detail-layout">
      <img src="${producto.imagen}" alt="${producto.nombre}" />
      <div class="detail-info">
        <h1>${producto.nombre}</h1>
        <p class="price">$${producto.precio.toFixed(2)}</p>
        <span class="tag-pill ${noDisponible ? 'no' : 'ok'}">${noDisponible ? 'No disponible' : `Stock: ${producto.stock}`}</span>
        <p>${producto.descripcion}</p>
        <div class="qty-selector">
          <button id="qty-minus" type="button" class="btn-qty">−</button>
          <span id="qty-value" class="qty-display">1</span>
          <button id="qty-plus" type="button" class="btn-qty">+</button>
        </div>
        <div id="alert-box"></div>
        <button id="btn-add" class="btn" ${noDisponible ? 'disabled' : ''}>Agregar al Carrito</button>
        <p style="margin-top:14px;"><a href="../home/home.html">← Volver al catálogo</a></p>
      </div>
    </div>
  `;

  const qtyValue = document.getElementById('qty-value')!;
  document.getElementById('qty-minus')!.addEventListener('click', () => {
    if (cantidad > 1) {
      cantidad--;
      qtyValue.textContent = String(cantidad);
    }
  });
  document.getElementById('qty-plus')!.addEventListener('click', () => {
    if (cantidad < producto.stock) {
      cantidad++;
      qtyValue.textContent = String(cantidad);
    }
  });

  document.getElementById('btn-add')!.addEventListener('click', () => {
    addToCart(producto.id, cantidad);
    updateCartBadge();
    document.getElementById('alert-box')!.innerHTML =
      `<div class="form-success">"${producto.nombre}" agregado al carrito (x${cantidad}).</div>`;
  });
}
