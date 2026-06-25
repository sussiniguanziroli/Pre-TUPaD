# Food Store JPA — Plantilla TPI (Parte 2)

Este README corresponde a la plantilla base para el desarrollo del TPI — Parte 2 (Backend JPA + Consola).

---

## Tecnologías

- Java 21
- JPA / Hibernate 6
- H2 (base de datos en archivo — `./data/jpa_db`)
- Lombok
- Gradle 8

---

## Estructura del proyecto

```
src/main/java/com/tp/jpa/
│
├── model/                        # Entidades JPA (NO modificar)
│   ├── Base.java                 # Clase abstracta base (id, eliminado, createdAt)
│   ├── Calculable.java           # Interfaz con calcularTotal()
│   ├── Categoria.java
│   ├── Producto.java
│   ├── Usuario.java
│   ├── Pedido.java
│   ├── DetallePedido.java
│   └── enums/
│       ├── Rol.java
│       ├── Estado.java
│       └── FormaPago.java
│
├── util/
│   └── JPAUtil.java              # Factory singleton (NO modificar — ya implementado)
│
├── repository/                   # ★ COMPLETAR — queries personalizadas
│   ├── BaseRepository.java       # CRUD genérico (NO modificar — ya implementado)
│   ├── ProductoRepository.java   # Sin queries extra (NO modificar)
│   ├── CategoriaRepository.java  # ★ Implementar buscarProductosPorCategoria()
│   ├── UsuarioRepository.java    # ★ Implementar buscarPorMail() y buscarPedidosPorUsuario()
│   └── PedidoRepository.java     # ★ Implementar buscarPorEstado()
│
└── Main.java                     # ★ COMPLETAR — menús de consola
```

---

## Qué está implementado

| Componente | Estado |
|---|---|
| `JPAUtil` | ✅ Completo |
| `BaseRepository` (guardar, buscarPorId, listarActivos, eliminarLogico) | ✅ Completo |
| Modelo completo (todas las entidades y enums) | ✅ Completo |
| `CategoriaRepository` (`buscarProductosPorCategoria`, `buscarCategoriaPorProducto`) | ✅ Completo |
| `UsuarioRepository` (`buscarPorMail`, `buscarPedidosPorUsuario`, `buscarUsuarioPorPedido`) | ✅ Completo |
| `PedidoRepository` (`buscarPorEstado`) | ✅ Completo |
| `Main` — los 5 submenús completos (Categorías, Productos, Usuarios, Pedidos, Reportes) | ✅ Completo |

Nota de diseño: como `Producto` no tiene un campo propio hacia `Categoria` ni `Pedido` hacia `Usuario`
(la relación es unidireccional desde el lado "uno", que es quien posee la colección), se agregaron dos
métodos auxiliares (`buscarCategoriaPorProducto` y `buscarUsuarioPorPedido`) para poder mostrar esos
nombres en los listados. El alta de `Producto` y de `Pedido` se hace agregando la entidad nueva a la
colección administrada del padre (`Categoria`/`Usuario`) dentro de una única transacción, en vez de usar
`productoRepo.guardar()` / construir el hijo de forma aislada, ya que es la única forma de que Hibernate
fije la FK correspondiente.

---

## Probado manualmente

Se verificó un flujo completo por consola: alta de categoría → alta de producto asociado → alta de
usuario → alta de pedido con cantidad > 1 (cálculo de subtotal/total correcto) → listado de pedidos
(muestra el nombre de usuario vía el JOIN) → reporte de total facturado. Compila con `gradlew compileJava`
sin errores.

Si al ejecutar en PowerShell ves acentos como "?" en lugar de "á/é/í/ó/ú/ñ", corré `chcp 65001` antes de
`./gradlew run`, o ejecutá la app desde la consola integrada de IntelliJ (maneja UTF-8 sin configuración
extra). Es solo un tema de visualización de la terminal, no afecta los datos ni la lógica.

---

## Cómo ejecutar

```bash
./gradlew run
```

O compilar y ejecutar el JAR:

```bash
./gradlew jar
java -jar build/libs/foodstore-jpa-0.0.1-SNAPSHOT.jar
```

La base de datos H2 se crea automáticamente en `./data/jpa_db.mv.db` al primer arranque.

---

## Credenciales / datos de prueba

No hay carga inicial automática. Crear los datos desde el menú de consola en este orden:

1. Categorías
2. Productos (requieren categoría existente)
3. Usuarios
4. Pedidos (requieren usuario y productos existentes)

---

## Entrega

- **Video demostrativo:** [link aquí]
- **Informe PDF:** [link aquí]
