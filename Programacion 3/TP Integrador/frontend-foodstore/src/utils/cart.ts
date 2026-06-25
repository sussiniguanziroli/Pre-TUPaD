import type { CartItem } from '../types/cart';

const KEY_CART = 'foodstore_cart';

export function getCart(): CartItem[] {
  const raw = localStorage.getItem(KEY_CART);
  return raw ? (JSON.parse(raw) as CartItem[]) : [];
}

function saveCart(items: CartItem[]): void {
  localStorage.setItem(KEY_CART, JSON.stringify(items));
}

export function addToCart(productoId: number, cantidad: number): void {
  const items = getCart();
  const existente = items.find(i => i.productoId === productoId);
  if (existente) {
    existente.cantidad += cantidad;
  } else {
    items.push({ productoId, cantidad });
  }
  saveCart(items);
}

export function updateCartQuantity(productoId: number, cantidad: number): void {
  const items = getCart().map(i => (i.productoId === productoId ? { ...i, cantidad } : i));
  saveCart(items);
}

export function removeFromCart(productoId: number): void {
  saveCart(getCart().filter(i => i.productoId !== productoId));
}

export function clearCart(): void {
  saveCart([]);
}

export function cartItemCount(): number {
  return getCart().reduce((total, i) => total + i.cantidad, 0);
}

/** Costo de envío fijo de esta iteración (ver README del frontend). */
export const ENVIO = 500;
