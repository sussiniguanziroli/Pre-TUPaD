import { exigirSesion, fetchUsuariosCombinados } from '../../../utils/auth';
import { fetchCategorias, fetchPedidos, fetchProductos } from '../../../utils/api';
import { renderAdminLayout } from '../../../utils/layout';
import { combinarOverlay } from '../../../utils/localOverlay';
import { ESTADOS } from '../../../utils/format';

const sesion = exigirSesion(['ADMIN']);
if (sesion) {
  renderAdminLayout(document.getElementById('header')!, sesion, 'dashboard');
  void init();
}

async function init(): Promise<void> {
  const [categoriasBase, productosBase, pedidosBase, usuarios] = await Promise.all([
    fetchCategorias(), fetchProductos(), fetchPedidos(), fetchUsuariosCombinados()
  ]);
  const categorias = combinarOverlay('categorias', categoriasBase);
  const productos = combinarOverlay('productos', productosBase);
  const pedidos = combinarOverlay('pedidos', pedidosBase);

  const categoriasActivas = categorias.filter(c => !c.eliminado);
  const productosActivos = productos.filter(p => !p.eliminado);
  const disponibles = productosActivos.filter(p => p.disponible);

  const porEstado = ESTADOS.map(e => ({ estado: e, cantidad: pedidos.filter(p => p.estado === e).length }));

  document.getElementById('admin-content')!.innerHTML = `
    <h1>Panel de Administración</h1>
    <div class="stat-cards">
      <div class="stat-card c1"><div class="stat-label">📁 Categorías</div><div class="stat-value">${categoriasActivas.length}</div></div>
      <div class="stat-card c2"><div class="stat-label">🍔 Productos</div><div class="stat-value">${productosActivos.length}</div></div>
      <div class="stat-card c3"><div class="stat-label">📦 Pedidos</div><div class="stat-value">${pedidos.length}</div></div>
      <div class="stat-card c4"><div class="stat-label">✅ Disponibles</div><div class="stat-value">${disponibles.length}</div></div>
    </div>
    <div class="panel">
      <h3>Resumen Rápido</h3>
      <div class="summary-row"><span>Categorías activas</span><span>${categoriasActivas.length} / ${categorias.length}</span></div>
      <div class="summary-row"><span>Productos disponibles / no disponibles</span><span>${disponibles.length} / ${productosActivos.length - disponibles.length}</span></div>
      <div class="summary-row"><span>Usuarios registrados</span><span>${usuarios.length}</span></div>
      ${porEstado.map(s => `<div class="summary-row"><span>Pedidos ${s.estado}</span><span>${s.cantidad}</span></div>`).join('')}
    </div>
  `;
}
