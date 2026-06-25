import { exigirSesion } from '../../../utils/auth';
import { fetchPedidos, fetchProductos } from '../../../utils/api';
import { renderStoreHeader, updateCartBadge } from '../../../utils/layout';
import { ENVIO, clearCart, getCart, removeFromCart, updateCartQuantity } from '../../../utils/cart';
import { combinarOverlay, crearEnOverlay } from '../../../utils/localOverlay';
import type { FormaPago, Pedido } from '../../../types/pedido';
import type { Producto } from '../../../types/producto';

const sesion = exigirSesion();
if (sesion) {
  renderStoreHeader(document.getElementById('header')!, sesion);
  void init();
}

async function init(): Promise<void> {
  const productosBase = await fetchProductos();
  const productos = combinarOverlay('productos', productosBase);
  renderCart(productos);

  document.getElementById('btn-clear')!.addEventListener('click', () => {
    if (getCart().length === 0) return;
    clearCart();
    updateCartBadge();
    renderCart(productos);
  });

  document.getElementById('btn-checkout')!.addEventListener('click', async () => {
    if (getCart().length === 0) return;
    const { total } = calcularTotales(productos);
    const todosPedidos = await fetchPedidos();
    abrirModalCheckout(total, (datos) => {
      const items = getCart();
      const nuevoPedido = crearEnOverlay<Pedido>(
        'pedidos',
        {
          fecha: new Date().toISOString().slice(0, 10),
          estado: 'PENDIENTE',
          total,
          formaPago: datos.formaPago,
          idUsuario: sesion!.id,
          detalles: items.map(i => {
            const producto = productos.find(p => p.id === i.productoId)!;
            return { idProducto: i.productoId, cantidad: i.cantidad, subtotal: producto.precio * i.cantidad };
          }),
          telefono: datos.telefono,
          direccion: datos.direccion,
          notas: datos.notas
        },
        todosPedidos.map(p => p.id)
      );
      clearCart();
      updateCartBadge();
      location.href = `../../client/orders/orders.html?creado=${nuevoPedido.id}`;
    });
  });
}

function calcularTotales(productos: Producto[]): { subtotal: number; total: number } {
  const subtotal = getCart().reduce((acc, item) => {
    const producto = productos.find(p => p.id === item.productoId);
    return acc + (producto ? producto.precio * item.cantidad : 0);
  }, 0);
  return { subtotal, total: subtotal + ENVIO };
}

function renderCart(productos: Producto[]): void {
  const container = document.getElementById('cart-container')!;
  const items = getCart();

  if (items.length === 0) {
    container.innerHTML = `<div class="empty-state">
      Tu carrito está vacío.<br />
      <a href="../home/home.html" class="btn">Ir a la tienda</a>
    </div>`;
  } else {
    container.innerHTML = items.map(item => {
      const producto = productos.find(p => p.id === item.productoId);
      if (!producto) return '';
      return `
        <div class="cart-item" data-id="${producto.id}">
          <img src="${producto.imagen}" alt="${producto.nombre}" />
          <div class="cart-item-info">
            <h3>${producto.nombre}</h3>
            <p>Precio: $${producto.precio.toFixed(2)}</p>
            <p>Subtotal: $${(producto.precio * item.cantidad).toFixed(2)}</p>
          </div>
          <div class="cart-item-controls">
            <button class="btn-qty btn-restar" type="button">−</button>
            <span class="qty-display">${item.cantidad}</span>
            <button class="btn-qty btn-sumar" type="button" ${item.cantidad >= producto.stock ? 'disabled' : ''}>+</button>
            <button class="btn-eliminar" type="button">Eliminar</button>
          </div>
        </div>
      `;
    }).join('');

    container.querySelectorAll<HTMLElement>('.cart-item').forEach(row => {
      const id = Number(row.dataset.id);
      const producto = productos.find(p => p.id === id)!;
      row.querySelector('.btn-restar')!.addEventListener('click', () => {
        const item = getCart().find(i => i.productoId === id);
        if (!item) return;
        if (item.cantidad <= 1) {
          removeFromCart(id);
        } else {
          updateCartQuantity(id, item.cantidad - 1);
        }
        updateCartBadge();
        renderCart(productos);
      });
      row.querySelector('.btn-sumar')!.addEventListener('click', () => {
        const item = getCart().find(i => i.productoId === id);
        if (!item || item.cantidad >= producto.stock) return;
        updateCartQuantity(id, item.cantidad + 1);
        updateCartBadge();
        renderCart(productos);
      });
      row.querySelector('.btn-eliminar')!.addEventListener('click', () => {
        removeFromCart(id);
        updateCartBadge();
        renderCart(productos);
      });
    });
  }

  const { subtotal, total } = calcularTotales(productos);
  document.getElementById('sum-subtotal')!.textContent = `$${subtotal.toFixed(2)}`;
  document.getElementById('sum-envio')!.textContent = `$${ENVIO.toFixed(2)}`;
  document.getElementById('sum-total')!.textContent = `$${total.toFixed(2)}`;
  (document.getElementById('btn-checkout') as HTMLButtonElement).disabled = items.length === 0;
}

interface DatosCheckout {
  telefono: string;
  direccion: string;
  formaPago: FormaPago;
  notas: string;
}

function abrirModalCheckout(total: number, onConfirm: (datos: DatosCheckout) => void): void {
  const root = document.getElementById('modal-root')!;
  root.innerHTML = `
    <div class="modal-overlay">
      <div class="modal-box">
        <button class="modal-close" id="modal-close">✕</button>
        <h3>Completar Pedido</h3>
        <div id="checkout-error"></div>
        <form id="checkout-form">
          <div class="field">
            <label for="telefono">Teléfono</label>
            <input id="telefono" type="text" placeholder="Ej: 11-1234-5678" required />
          </div>
          <div class="field">
            <label for="direccion">Dirección de Entrega</label>
            <input id="direccion" type="text" placeholder="Calle, número, piso, depto" required />
          </div>
          <div class="field">
            <label for="formaPago">Método de Pago</label>
            <select id="formaPago" required>
              <option value="">Seleccione un método</option>
              <option value="TARJETA">Tarjeta</option>
              <option value="TRANSFERENCIA">Transferencia</option>
              <option value="EFECTIVO">Efectivo</option>
            </select>
          </div>
          <div class="field">
            <label for="notas">Notas adicionales (opcional)</label>
            <textarea id="notas" rows="2" placeholder="Instrucciones especiales, timbre, etc."></textarea>
          </div>
          <p style="margin-bottom:12px;"><strong>Total a pagar: $${total.toFixed(2)}</strong></p>
          <button type="submit" class="btn btn-success">Confirmar Pedido</button>
        </form>
      </div>
    </div>
  `;

  function cerrar() {
    root.innerHTML = '';
  }
  document.getElementById('modal-close')!.addEventListener('click', cerrar);

  document.getElementById('checkout-form')!.addEventListener('submit', (e) => {
    e.preventDefault();
    const telefono = (document.getElementById('telefono') as HTMLInputElement).value.trim();
    const direccion = (document.getElementById('direccion') as HTMLInputElement).value.trim();
    const formaPago = (document.getElementById('formaPago') as HTMLSelectElement).value as FormaPago;
    const notas = (document.getElementById('notas') as HTMLTextAreaElement).value.trim();
    const errorBox = document.getElementById('checkout-error')!;

    if (!telefono || !direccion || !formaPago) {
      errorBox.innerHTML = `<div class="form-error">Completá teléfono, dirección y método de pago.</div>`;
      return;
    }
    onConfirm({ telefono, direccion, formaPago, notas });
    cerrar();
  });
}
