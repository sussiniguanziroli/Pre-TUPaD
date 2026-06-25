import type { Estado } from '../types/pedido';

export function formatMoney(valor: number): string {
  return `$${valor.toFixed(2)}`;
}

export function formatFecha(fecha: string): string {
  const d = new Date(fecha + 'T00:00:00');
  if (Number.isNaN(d.getTime())) return fecha;
  return d.toLocaleDateString('es-AR', { day: '2-digit', month: 'long', year: 'numeric' });
}

const ESTADO_LABEL: Record<Estado, string> = {
  PENDIENTE: 'Pendiente',
  CONFIRMADO: 'Confirmado',
  TERMINADO: 'Terminado',
  CANCELADO: 'Cancelado'
};

const ESTADO_CLASS: Record<Estado, string> = {
  PENDIENTE: 'status-pendiente',
  CONFIRMADO: 'status-confirmado',
  TERMINADO: 'status-terminado',
  CANCELADO: 'status-cancelado'
};

export function estadoBadgeHtml(estado: Estado): string {
  return `<span class="status-badge ${ESTADO_CLASS[estado]}">${ESTADO_LABEL[estado]}</span>`;
}

export const ESTADOS: Estado[] = ['PENDIENTE', 'CONFIRMADO', 'TERMINADO', 'CANCELADO'];
