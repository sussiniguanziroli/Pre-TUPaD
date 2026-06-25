# Food Store — Frontend (Parte 1)

Frontend del TPI Food Store. TypeScript + Vite, sin frameworks. Consume datos desde archivos
`.json` locales (`public/data/`) mediante `fetch()`, simulando la futura API REST.

## Tecnologías

- TypeScript + Vite (app multi-página, una entrada HTML por pantalla)
- HTML5 + CSS3 (sin Tailwind; estilos propios en `src/css/main.css`, mismo estilo "glass" oscuro
  usado en el Primer Parcial de la materia)
- Persistencia de sesión, carrito y operaciones de escritura en `localStorage`

## Cómo ejecutar

```bash
npm install
npm run dev
```

Abre `http://localhost:5173`. La build de producción se genera con `npm run build` (multi-página,
ver `vite.config.ts`).

## Credenciales de prueba

| Rol     | Mail               | Contraseña  |
|---------|--------------------|-------------|
| ADMIN   | admin@admin.com    | 123456      |
| USUARIO | cliente@food.com   | cliente123  |
| USUARIO | maria@food.com     | maria123    |

También se puede crear una cuenta nueva desde "Registrarme" (queda guardada solo en este navegador).

## Estructura

Sigue la estructura pedida en la consigna (`src/pages/{auth,store,client,admin}/...`) combinada con
la convención usada en el Primer Parcial de la materia: cada pantalla es un par
`<pagina>.html` + `<pagina>.ts` dentro de su propia carpeta (no `index.html`), el CSS vive en un único
archivo (`src/css/main.css`) enlazado con `<link>` en cada HTML (no se importa desde TypeScript), y
los tipos están separados por entidad en `src/types/` (`categoria.ts`, `producto.ts`, `usuario.ts`,
`pedido.ts`, `cart.ts`), igual que `product.ts`/`categoria.ts` en aquel proyecto. El código de cada
página usa funciones planas (sin clases) con el patrón `render()` + `addEventListener` reaplicado
después de cada render, igual que `home.ts`/`cart.ts` del parcial. No hay router de SPA porque la
consigna pide páginas separadas navegables por URL.

## Decisiones de diseño

- **Costo de envío fijo**: `ENVIO = 500` (constante en `src/utils/cart.ts`). El total del carrito y
  del pedido generado es `subtotal + envío`.
- **Capa de fetch aislada** (`src/utils/api.ts`): toda lectura de datos pasa por ahí. Para conectar
  el backend de la Parte 2 alcanza con cambiar las constantes de `ENDPOINTS` por las rutas de la API
  (ej. `/data/productos.json` → `/api/products`); el resto de la app no se entera del cambio.
- **Operaciones de escritura ("en memoria")**: la consigna pide que el alta/edición/baja de
  categorías, productos y pedidos no toque los `.json` y se pierda al recargar. Como esta es una app
  multi-página (cada navegación es una carga de documento nueva, no un SPA), una variable en memoria
  de JavaScript no sobreviviría ni siquiera a un click en el menú. Para que el panel de administración
  sea usable se implementó el mismo concepto pero apoyado en `localStorage`
  (`src/utils/localOverlay.ts`): un "overlay" de creados/editados que se combina con lo leído del
  `.json` en cada fetch. Esto también permite que un producto o categoría creado en el panel admin
  se vea reflejado de inmediato en la tienda (más coherente para la demo). Sigue sin persistir en los
  archivos `.json` reales, que es el punto central de la consigna; lo único que cambia es la frontera
  de "memoria" (de la pestaña → del navegador). Esto se documenta como aclaración para la corrección.
- **Registro de usuarios**: tampoco se persiste en `usuarios.json` (igual que el resto de las
  escrituras); se guarda en `localStorage` bajo una clave separada y se usa solo para validar logins
  futuros en este navegador.
- **Roles**: el campo `rol` del usuario en sesión (`localStorage`) determina la UI visible y redirige
  a quien intenta acceder a una sección sin permiso (ver `src/utils/auth.ts#exigirSesion`). Es
  seguridad de UI únicamente, según lo aclarado en la consigna (F7): no hay backend real validando esto.

## Datos de prueba (`public/data/`)

Se incluyen 5 categorías, 19 productos (uno marcado `disponible: false` para poder probar ese caso),
3 usuarios y 4 pedidos con distintos estados (`PENDIENTE`, `CONFIRMADO`, `TERMINADO`, `CANCELADO`)
para poder mostrar todos los badges y flujos en la demo.
