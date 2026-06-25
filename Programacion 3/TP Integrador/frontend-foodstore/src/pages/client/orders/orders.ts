import { exigirSesion } from '../../../utils/auth';
import { fetchPedidos, fetchProductos } from '../../../utils/api';
import { renderStoreHeader } from '../../../utils/layout';
import { estadoBadgeHtml, formatFecha, formatMoney } from '../../../utils/format';
import { combinarOverlay } from '../../../utils/localOverlay';
import type { Estado, Pedido } from '../../../types/pedido';
import type { Producto } from '../../../types/producto';

const sesion = exigirSesion();
if (sesion) {
  renderStoreHeader(document.getElementById('header')!, sesion);
  void init();
}

async function init(): Promise<void> {
  const [pedidosBase, productosBase] = await Promise.all([fetchPedidos(), fetchProductos()]);
  const todosLosPedidos = combinarOverlay('pedidos', pedidosBase);
  const productos = combinarOverlay('productos', productosBase);
  const misPedidos = todosLosPedidos
    .filter(p => p.idUsuario === sesion!.id)
    .sort((a, b) => b.fecha.localeCompare(a.fecha));

  let filtro: Estado | '' = '';

  render();

  document.getElementById('filter-estado')!.addEventListener('change', (e) => {
    filtro = (e.target as HTMLSelectElement).value as Estado | '';
    render();
  });

  function render(): void {
    const lista = document.getElementById('orders-list')!;
    const visibles = misPedidos.filter(p => !filtro || p.estado === filtro);

    if (visibles.length === 0) {
      lista.innerHTML = `<div class="empty-state">
        ${misPedidos.length === 0 ? 'Todavía no realizaste ningún pedido.' : 'No hay pedidos con ese estado.'}
        <br /><a href="../../store/home/home.html" class="btn">Ir a la tienda</a>
      </div>`;
      return;
    }

    lista.innerHTML = visibles.map(p => {
      const nombresProductos = p.detalles.slice(0, 3)
        .map(d => productos.find(prod => prod.id === d.idProducto)?.nombre ?? 'Producto')
        .join(', ');
      return `
        <div class="order-card" data-id="${p.id}">
          <div class="order-head">
            <strong>Pedido #ORD-${p.id}</strong>
            ${estadoBadgeHtml(p.estado)}
          </div>
          <div class="order-meta">📅 ${formatFecha(p.fecha)}</div>
          <div class="order-meta">${nombresProductos}</div>
          <div class="order-foot">
            <span class="order-meta">${p.detalles.length} producto(s)</span>
            <span class="order-total">${formatMoney(p.total)}</span>
          </div>
        </div>
      `;
    }).join('');

    lista.querySelectorAll<HTMLElement>('.order-card').forEach(card => {
      card.addEventListener('click', () => {
        const pedido = misPedidos.find(p => p.id === Number(card.dataset.id))!;
        abrirDetalle(pedido, productos);
      });
    });
  }
}

function abrirDetalle(pedido: Pedido, productos: Producto[]): void {
  const root = document.getElementById('modal-root')!;
  root.innerHTML = `
    <div class="modal-overlay">
      <div class="modal-box">
        <button class="modal-close" id="modal-close">✕</button>
        <h3>Detalle del Pedido #ORD-${pedido.id}</h3>
        ${estadoBadgeHtml(pedido.estado)}
        <p style="margin-top:10px;">📅 <strong>Fecha:</strong> ${formatFecha(pedido.fecha)}</p>
        ${pedido.direccion ? `<p>📍 <strong>Dirección:</strong> ${pedido.direccion}</p>` : ''}
        ${pedido.telefono ? `<p>📞 <strong>Teléfono:</strong> ${pedido.telefono}</p>` : ''}
        <p>💳 <strong>Método de pago:</strong> ${pedido.formaPago}</p>
        <h4>Productos</h4>
        ${pedido.detalles.map(d => {
          const producto = productos.find(p => p.id === d.idProducto);
          return `<div class="summary-row"><span>${producto?.nombre ?? 'Producto'} x${d.cantidad}</span><span>${formatMoney(d.subtotal)}</span></div>`;
        }).join('')}
        <div class="summary-row total"><span>Total</span><span>${formatMoney(pedido.total)}</span></div>
      </div>
    </div>
  `;
  document.getElementById('modal-close')!.addEventListener('click', () => { root.innerHTML = ''; });
}
