# Parcial JPA - Programación III - UTN

## Descripción

Sistema de gestión de categorías y productos desarrollado en Java con JPA (Hibernate) y base de datos H2.
Permite realizar operaciones ABM (Alta, Baja, Modificación) sobre Categorías y Productos desde un menú de consola,
e incluye una consulta JPQL para filtrar productos por categoría.

## Tecnologías utilizadas

- Java 21
- Gradle
- Hibernate ORM 6.4.4
- H2 Database
- Lombok

## Estructura del proyecto

```
src/main/java/com/tp/jpa/
├── model/          # Entidades JPA (Base, Categoria, Producto, etc.)
├── model/enums/    # Enumeraciones (Rol, Estado, FormaPago)
├── repository/     # Repositorios (BaseRepository, CategoriaRepository, ProductoRepository)
├── util/           # JPAUtil (manejo del EntityManagerFactory)
└── Main.java       # Clase principal con menú de consola
```


## Instrucciones para ejecutar

1. Clonar o descomprimir el proyecto
2. Abrir con IntelliJ IDEA
3. Sincronizar dependencias Gradle
4. Ejecutar la clase `Main.java` desde `src/main/java/com/tp/jpa/`

La base de datos H2 se crea automáticamente en la carpeta `data/` al iniciar la aplicación.

## Funcionalidades

- **Categorías:** Alta, Baja lógica, Modificación y Listado
- **Productos:** Alta, Baja lógica, Modificación y Listado
- **Reportes:** Consulta JPQL de productos activos filtrados por categoría