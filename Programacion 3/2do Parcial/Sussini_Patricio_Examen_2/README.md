# Parcial 2 - Programación III | JPA: Repositorios y ABM

**Alumno:** Patricio María Sussini Guanziroli  
**Materia:** Programación III  
**Tecnologías:** Java 17, Maven, Hibernate 6, Jakarta JPA 3, H2, Lombok

## Descripción

Extensión del TP8 de JPA. Se agregan repositorios genéricos y específicos para Categoría y Producto,
e implementa un menú de consola con ABM completo y una consulta JPQL personalizada.

### Archivos nuevos
| Archivo | Paquete | Rol |
|---|---|---|
| `JPAUtil.java` | `com.utn.util` | Singleton del EntityManagerFactory |
| `BaseRepository.java` | `com.utn.repository` | CRUD genérico con Optional y baja lógica |
| `CategoriaRepository.java` | `com.utn.repository` | Repositorio de Categoria |
| `ProductoRepository.java` | `com.utn.repository` | Repositorio de Producto + JPQL por categoría |
| `Main.java` | `com.utn` | Menú de consola con ABM y Reportes |

## Cómo ejecutar

### Requisitos
- Java 17+
- Maven 3.8+

### Compilar
```bash
mvn compile
```

### Ejecutar
```bash
mvn exec:java
```

La base de datos H2 se guarda en `./data/jpa_db` y persiste entre ejecuciones.

## Menú de la aplicación

```
MENU PRINCIPAL
  1. Categorias  →  Alta / Baja lógica / Modificación / Listado
  2. Productos   →  Alta / Baja lógica / Modificación / Listado
  3. Reportes    →  Productos por categoría (JPQL)
  0. Salir
```
