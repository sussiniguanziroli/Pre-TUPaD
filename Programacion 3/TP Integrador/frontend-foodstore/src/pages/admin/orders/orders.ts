import { exigirSesion, fetchUsuariosCombinados } from '../../../utils/auth';
import { fetchPedidos, fetchProductos } from '../../../utils/api';
import { renderAdminLayout } from '../../../utils/layout';
import { actualizarEnOverlay, combinarOverlay } from '../../../utils/localOverlay';
import { ESTADOS, estadoBadgeHtml, formatFecha, formatMoney } from '../../../utils/format';
import type { Estado, Pedido } from '../../../types/pedido';
import type { Producto } from '../../../types/producto';
import type { Usuario } from '../../../types/usuario';

const sesion = exigirSesion(['ADMIN']);
if (sesion) {
  renderAdminLayout(document.getElementById('header')!, sesion, 'orders');
  void init();
}

async function init(): Promise<void> {
  const [pedidosBase, usuarios, productosBase] = await Promise.all([fetchPedidos(), fetchUsuariosCombinados(), fetchProductos()]);
  const productos = combinarOverlay('productos', productosBase);
  let filtro: Estado | '' = '';

  render();

  function render(): void {
    const pedidos = combinarOverlay('pedidos', pedidosBase)
      .filter(p => !filtro || p.estado === filtro)
      .sort((a, b) => b.fecha.localeCompare(a.fecha));

    const content = document.getElementById('admin-content')!;
    content.innerHTML = `
      <div class="admin-toolbar">
        <h1>Gestión de Pedidos</h1>
        <select id="filter-estado">
          <option value="">Todos los pedidos</option>
          ${ESTADOS.map(e => `<option value="${e}">${e}</option>`).join('')}
        </select>
      </div>
      <div id="orders-list"></div>
    `;

    document.getElementById('filter-estado')!.addEventListener('change', (e) => {
      filtro = (e.target as HTMLSelectElement).value as Estado | '';
      render();
    });

    const lista = document.getElementById('orders-list')!;
    if (pedidos.length === 0) {
      lista.innerHTML = `<div class="empty-state">No hay pedidos para mostrar.</div>`;
      return;
    }

    lista.innerHTML = pedidos.map(p => {
      const cliente = usuarios.find(u => u.id === p.idUsuario);
      return `
        <div class="order-card" data-id="${p.id}">
          <div class="order-head">
            <strong>Pedido #ORD-${p.id}</strong>
            ${estadoBadgeHtml(p.estado)}
          </div>
          <div class="order-meta">Cliente: ${cliente ? `${cliente.nombre} ${cliente.apellido}` : 'Desconocido'}</div>
          <div class="order-meta">📅 ${formatFecha(p.fecha)}</div>
          <div class="order-foot">
            <span class="order-meta">${p.detalles.length} producto(s)</span>
            <span class="order-total">${formatMoney(p.total)}</span>
          </div>
        </div>
      `;
    }).join('');

    lista.querySelectorAll<HTMLElement>('.order-card').forEach(card => {
      card.addEventListener('click', () => {
        const pedido = pedidos.find(p => p.id === Number(card.dataset.id))!;
        abrirDetalle(pedido, usuarios, productos, render);
      });
    });
  }
}

function abrirDetalle(pedido: Pedido, usuarios: Usuario[], productos: Producto[], onUpdated: () => void): void {
  const cliente = usuarios.find(u => u.id === pedido.idUsuario);
  const root = document.getElementById('modal-root')!;
  root.innerHTML = `
    <div class="modal-overlay">
      <div class="modal-box">
        <button class="modal-close" id="modal-close">✕</button>
        <h3>Detalle del Pedido #ORD-${pedido.id}</h3>
        <p><strong>Cliente:</strong> ${cliente ? `${cliente.nombre} ${cliente.apellido}` : 'Desconocido'}</p>
        <p><strong>Fecha:</strong> ${formatFecha(pedido.fecha)}</p>
        ${pedido.telefono ? `<p><strong>Teléfono:</strong> ${pedido.telefono}</p>` : ''}
        ${pedido.direccion ? `<p><strong>Dirección:</strong> ${pedido.direccion}</p>` : ''}
        <p><strong>Método de pago:</strong> ${pedido.formaPago}</p>
        <h4>Productos</h4>
        ${pedido.detalles.map(d => {
          const producto = productos.find(p => p.id === d.idProducto);
          return `<div class="summary-row"><span>${producto?.nombre ?? 'Producto'} x${d.cantidad}</span><span>${formatMoney(d.subtotal)}</span></div>`;
        }).join('')}
        <div class="summary-row total"><span>Total</span><span>${formatMoney(pedido.total)}</span></div>
        <div class="field" style="margin-top:16px;">
          <label for="nuevo-estado">Cambiar Estado</label>
          <select id="nuevo-estado">
            ${ESTADOS.map(e => `<option value="${e}" ${e === pedido.estado ? 'selected' : ''}>${e}</option>`).join('')}
          </select>
        </div>
        <button id="btn-update" class="btn btn-success">Actualizar Estado</button>
      </div>
    </div>
  `;
  document.getElementById('modal-close')!.addEventListener('click', () => { root.innerHTML = ''; });
  document.getElementById('btn-update')!.addEventListener('click', () => {
    const nuevoEstado = (document.getElementById('nuevo-estado') as HTMLSelectElement).value as Estado;
    actualizarEnOverlay<Pedido>('pedidos', pedido.id, { estado: nuevoEstado });
    root.innerHTML = '';
    onUpdated();
  });
}
