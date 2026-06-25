/**
 * Las operaciones de escritura (crear/editar/dar de baja) de esta iteración
 * no tocan los .json: se aplican sobre un "overlay" en localStorage que se
 * combina con los datos leídos por fetch(). Se usa el mismo mecanismo para
 * Categorías, Productos y Pedidos (admin y checkout) para no repetir la
 * lógica de merge en cada página.
 */

interface ConId {
  id: number;
}

function leer<T>(key: string, valorPorDefecto: T): T {
  const raw = localStorage.getItem(key);
  return raw ? (JSON.parse(raw) as T) : valorPorDefecto;
}

function escribir(key: string, valor: unknown): void {
  localStorage.setItem(key, JSON.stringify(valor));
}

function creadosKey(entidad: string): string {
  return `foodstore_overlay_${entidad}_created`;
}

function actualizacionesKey(entidad: string): string {
  return `foodstore_overlay_${entidad}_updates`;
}

function getCreados<T>(entidad: string): T[] {
  return leer<T[]>(creadosKey(entidad), []);
}

function getActualizaciones<T>(entidad: string): Record<number, Partial<T>> {
  return leer<Record<number, Partial<T>>>(actualizacionesKey(entidad), {});
}

/** Combina la lista base (del .json) con lo creado/editado localmente para esa entidad. */
export function combinarOverlay<T extends ConId>(entidad: string, base: T[]): T[] {
  const actualizaciones = getActualizaciones<T>(entidad);
  const baseConCambios = base.map(item =>
    actualizaciones[item.id] ? { ...item, ...actualizaciones[item.id] } : item
  );
  const creados = getCreados<T>(entidad).map(item =>
    actualizaciones[item.id] ? { ...item, ...actualizaciones[item.id] } : item
  );
  return [...baseConCambios, ...creados];
}

/** Crea una entidad nueva con un ID autoincremental respecto de la lista combinada. */
export function crearEnOverlay<T extends ConId>(entidad: string, datos: Omit<T, 'id'>, idsExistentes: number[]): T {
  const creados = getCreados<T>(entidad);
  const maxId = Math.max(0, ...idsExistentes, ...creados.map(c => c.id));
  const nuevo = { ...(datos as object), id: maxId + 1 } as T;
  creados.push(nuevo);
  escribir(creadosKey(entidad), creados);
  return nuevo;
}

/** Actualiza una entidad (creada localmente o proveniente del .json) por ID. */
export function actualizarEnOverlay<T extends ConId>(entidad: string, id: number, cambios: Partial<T>): void {
  const creados = getCreados<T>(entidad);
  const idx = creados.findIndex(c => c.id === id);
  if (idx >= 0) {
    creados[idx] = { ...creados[idx], ...cambios };
    escribir(creadosKey(entidad), creados);
    return;
  }
  const actualizaciones = getActualizaciones<T>(entidad);
  actualizaciones[id] = { ...actualizaciones[id], ...cambios };
  escribir(actualizacionesKey(entidad), actualizaciones);
}
