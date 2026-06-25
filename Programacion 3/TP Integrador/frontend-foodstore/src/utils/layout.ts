import type { SesionUsuario } from '../types/usuario';
import { logout } from './auth';
import { cartItemCount } from './cart';

const RUTA_LOGIN = '/src/pages/auth/login/login.html';
const RUTA_STORE_HOME = '/src/pages/store/home/home.html';
const RUTA_CLIENT_ORDERS = '/src/pages/client/orders/orders.html';
const RUTA_CART = '/src/pages/store/cart/cart.html';
const RUTA_ADMIN_HOME = '/src/pages/admin/adminHome/adminHome.html';

/** Header común a las páginas de tienda y del cliente (catálogo, detalle, carrito, mis pedidos). */
export function renderStoreHeader(target: HTMLElement, sesion: SesionUsuario): void {
  target.innerHTML = `
    <header>
      <a class="brand" href="${RUTA_STORE_HOME}">🍔 Food Store</a>
      <nav class="topnav">
        <a href="${RUTA_STORE_HOME}">Inicio</a>
        <a href="${RUTA_CLIENT_ORDERS}">Mis Pedidos</a>
        ${sesion.rol === 'ADMIN' ? `<a href="${RUTA_ADMIN_HOME}">Administración</a>` : ''}
        <a href="${RUTA_CART}">🛒 Carrito <span id="cart-badge" class="cart-badge">${cartItemCount()}</span></a>
        <span class="user-name">${sesion.nombre} ${sesion.apellido}</span>
        <button id="btn-logout" class="btn btn-outline">Cerrar Sesión</button>
      </nav>
    </header>
  `;
  document.getElementById('btn-logout')?.addEventListener('click', () => {
    logout();
    location.href = RUTA_LOGIN;
  });
}

export function updateCartBadge(): void {
  const badge = document.getElementById('cart-badge');
  if (badge) badge.textContent = String(cartItemCount());
}

const ADMIN_LINKS: Array<{ href: string; label: string; key: string }> = [
  { href: RUTA_ADMIN_HOME, label: 'Dashboard', key: 'dashboard' },
  { href: '/src/pages/admin/categories/categories.html', label: 'Categorías', key: 'categories' },
  { href: '/src/pages/admin/products/products.html', label: 'Productos', key: 'products' },
  { href: '/src/pages/admin/orders/orders.html', label: 'Pedidos', key: 'orders' }
];

/** Layout común a las páginas del panel de administración: topbar + sidebar. */
export function renderAdminLayout(target: HTMLElement, sesion: SesionUsuario, activeKey: string): void {
  target.innerHTML = `
    <header>
      <a class="brand" href="${RUTA_ADMIN_HOME}">🍔 Food Store</a>
      <nav class="topnav">
        <a href="${RUTA_STORE_HOME}">Tienda</a>
        <a href="${RUTA_ADMIN_HOME}">Panel Admin</a>
        <span class="user-name">${sesion.nombre} ${sesion.apellido}</span>
        <button id="btn-logout" class="btn btn-outline">Cerrar Sesión</button>
      </nav>
    </header>
    <div class="admin-shell">
      <aside class="sidebar">
        <p class="sidebar-title">Administración</p>
        ${ADMIN_LINKS.map(l => `<a class="${l.key === activeKey ? 'active' : ''}" href="${l.href}">${l.label}</a>`).join('')}
        <a href="${RUTA_STORE_HOME}">Ver Tienda</a>
      </aside>
      <section class="admin-content" id="admin-content"></section>
    </div>
  `;
  document.getElementById('btn-logout')?.addEventListener('click', () => {
    logout();
    location.href = RUTA_LOGIN;
  });
}
