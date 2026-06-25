import type { Categoria } from '../types/categoria';
import type { Producto } from '../types/producto';
import type { Usuario } from '../types/usuario';
import type { Pedido } from '../types/pedido';

/**
 * Capa de acceso a datos aislada en un único módulo: hoy lee archivos .json
 * locales con fetch(); cuando se conecte el backend de la Parte 2, esta es la
 * única pieza que cambia (las rutas '/data/xxx.json' pasan a ser '/api/xxx').
 */

const ENDPOINTS = {
  categorias: '/data/categorias.json',
  productos: '/data/productos.json',
  usuarios: '/data/usuarios.json',
  pedidos: '/data/pedidos.json'
} as const;

async function fetchJson<T>(url: string): Promise<T> {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`No se pudo obtener ${url} (status ${response.status})`);
  }
  return response.json() as Promise<T>;
}

export function fetchCategorias(): Promise<Categoria[]> {
  // const response = await fetch('/api/categorias');
  return fetchJson<Categoria[]>(ENDPOINTS.categorias);
}

export function fetchProductos(): Promise<Producto[]> {
  // const response = await fetch('/api/products');
  return fetchJson<Producto[]>(ENDPOINTS.productos);
}

export function fetchUsuarios(): Promise<Usuario[]> {
  // const response = await fetch('/api/users');
  return fetchJson<Usuario[]>(ENDPOINTS.usuarios);
}

export function fetchPedidos(): Promise<Pedido[]> {
  // const response = await fetch('/api/orders');
  return fetchJson<Pedido[]>(ENDPOINTS.pedidos);
}
