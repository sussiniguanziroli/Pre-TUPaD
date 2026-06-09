package com.utn;

import com.utn.entities.Categoria;
import com.utn.entities.Producto;
import com.utn.repository.CategoriaRepository;
import com.utn.repository.ProductoRepository;
import com.utn.util.JPAUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CategoriaRepository categoriaRepo = new CategoriaRepository();
    private static final ProductoRepository productoRepo = new ProductoRepository();

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> menuCategorias();
                case "2" -> menuProductos();
                case "3" -> menuReportes();
                case "0" -> salir = true;
                default  -> System.out.println("Opcion invalida.");
            }
        }
        JPAUtil.close();
        System.out.println("Hasta luego!");
    }

    // =========================================================
    // CATEGORIAS
    // =========================================================

    private static void menuCategorias() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU CATEGORIAS ---");
            System.out.println("1. Alta");
            System.out.println("2. Baja logica");
            System.out.println("3. Modificacion");
            System.out.println("4. Listado");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> altaCategoria();
                case "2" -> bajaCategoria();
                case "3" -> modificarCategoria();
                case "4" -> listarCategorias();
                case "0" -> volver = true;
                default  -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void altaCategoria() {
        System.out.println("\n-- Alta de Categoria --");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("ERROR: El nombre no puede estar vacio.");
            return;
        }
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();

        Categoria cat = Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .build();

        Categoria guardada = categoriaRepo.guardar(cat);
        System.out.println("Categoria creada exitosamente. ID generado: " + guardada.getId());
    }

    private static void bajaCategoria() {
        System.out.println("\n-- Baja de Categoria --");
        listarCategorias();

        System.out.print("Ingrese el ID de la categoria a dar de baja: ");
        Long id = parseLong(scanner.nextLine().trim());
        if (id == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Categoria> opt = categoriaRepo.buscarPorId(id);
        if (opt.isEmpty() || opt.get().isEliminado()) {
            System.out.println("ERROR: No se encontro una categoria activa con ID " + id + ".");
            return;
        }

        String nombre = opt.get().getNombre();
        categoriaRepo.eliminarLogico(id);
        System.out.println("Categoria '" + nombre + "' dada de baja correctamente.");
    }

    private static void modificarCategoria() {
        System.out.println("\n-- Modificacion de Categoria --");
        listarCategorias();

        System.out.print("Ingrese el ID de la categoria a modificar: ");
        Long id = parseLong(scanner.nextLine().trim());
        if (id == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Categoria> opt = categoriaRepo.buscarPorId(id);
        if (opt.isEmpty() || opt.get().isEliminado()) {
            System.out.println("ERROR: No se encontro una categoria activa con ID " + id + ".");
            return;
        }

        Categoria cat = opt.get();
        System.out.println("Valores actuales -> Nombre: " + cat.getNombre()
                + " | Descripcion: " + cat.getDescripcion());

        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) cat.setNombre(nuevoNombre);

        System.out.print("Nueva descripcion (Enter para mantener): ");
        String nuevaDesc = scanner.nextLine().trim();
        if (!nuevaDesc.isEmpty()) cat.setDescripcion(nuevaDesc);

        categoriaRepo.guardar(cat);
        System.out.println("Categoria actualizada correctamente.");
    }

    private static void listarCategorias() {
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas.");
            return;
        }
        System.out.println("\n-- Categorias activas --");
        System.out.printf("%-5s | %-20s | %-30s%n", "ID", "NOMBRE", "DESCRIPCION");
        System.out.println("-".repeat(62));
        categorias.forEach(c -> System.out.printf(
                "%-5d | %-20s | %-30s%n", c.getId(), c.getNombre(), c.getDescripcion()));
    }

    // =========================================================
    // PRODUCTOS
    // =========================================================

    private static void menuProductos() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU PRODUCTOS ---");
            System.out.println("1. Alta");
            System.out.println("2. Baja logica");
            System.out.println("3. Modificacion");
            System.out.println("4. Listado");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> altaProducto();
                case "2" -> bajaProducto();
                case "3" -> modificarProducto();
                case "4" -> listarProductos();
                case "0" -> volver = true;
                default  -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void altaProducto() {
        System.out.println("\n-- Alta de Producto --");
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas. Cree una categoria primero.");
            return;
        }
        System.out.println("Categorias disponibles:");
        categorias.forEach(c -> System.out.printf("[ID: %-3d] %s%n", c.getId(), c.getNombre()));

        System.out.print("ID de categoria: ");
        Long catId = parseLong(scanner.nextLine().trim());
        if (catId == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Categoria> catOpt = categoriaRepo.buscarPorId(catId);
        if (catOpt.isEmpty() || catOpt.get().isEliminado()) {
            System.out.println("ERROR: Categoria no encontrada o inactiva.");
            return;
        }

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) { System.out.println("ERROR: El nombre no puede estar vacio."); return; }

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();

        System.out.print("Precio (mayor a 0): ");
        Double precio = parseDouble(scanner.nextLine().trim());
        if (precio == null || precio <= 0) {
            System.out.println("ERROR: El precio debe ser un numero mayor a 0.");
            return;
        }

        System.out.print("Stock (mayor o igual a 0): ");
        Integer stock = parseInt(scanner.nextLine().trim());
        if (stock == null || stock < 0) {
            System.out.println("ERROR: El stock debe ser un numero mayor o igual a 0.");
            return;
        }

        Producto prod = Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .disponible(true)
                .categoria(catOpt.get())
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .build();

        Producto guardado = productoRepo.guardar(prod);
        System.out.println("Producto creado exitosamente. ID: " + guardado.getId()
                + " | Categoria: " + catOpt.get().getNombre());
    }

    private static void bajaProducto() {
        System.out.println("\n-- Baja de Producto --");
        listarProductos();

        System.out.print("Ingrese el ID del producto a dar de baja: ");
        Long id = parseLong(scanner.nextLine().trim());
        if (id == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Producto> opt = productoRepo.buscarPorId(id);
        if (opt.isEmpty() || opt.get().isEliminado()) {
            System.out.println("ERROR: No se encontro un producto activo con ID " + id + ".");
            return;
        }

        String nombre = opt.get().getNombre();
        productoRepo.eliminarLogico(id);
        System.out.println("Producto '" + nombre + "' dado de baja correctamente.");
    }

    private static void modificarProducto() {
        System.out.println("\n-- Modificacion de Producto --");
        listarProductos();

        System.out.print("Ingrese el ID del producto a modificar: ");
        Long id = parseLong(scanner.nextLine().trim());
        if (id == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Producto> opt = productoRepo.buscarPorId(id);
        if (opt.isEmpty() || opt.get().isEliminado()) {
            System.out.println("ERROR: No se encontro un producto activo con ID " + id + ".");
            return;
        }

        Producto prod = opt.get();
        System.out.printf("Valores actuales -> Nombre: %s | Precio: $%.2f | Stock: %d | Categoria: %s%n",
                prod.getNombre(), prod.getPrecio(), prod.getStock(),
                prod.getCategoria() != null ? prod.getCategoria().getNombre() : "-");

        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) prod.setNombre(nuevoNombre);

        System.out.print("Nuevo precio (Enter para mantener): ");
        String precioStr = scanner.nextLine().trim();
        if (!precioStr.isEmpty()) {
            Double nuevoPrecio = parseDouble(precioStr);
            if (nuevoPrecio == null || nuevoPrecio <= 0) {
                System.out.println("Precio invalido: debe ser mayor a 0. Se mantiene el valor anterior.");
            } else {
                prod.setPrecio(nuevoPrecio);
            }
        }

        System.out.print("Nuevo stock (Enter para mantener): ");
        String stockStr = scanner.nextLine().trim();
        if (!stockStr.isEmpty()) {
            Integer nuevoStock = parseInt(stockStr);
            if (nuevoStock == null || nuevoStock < 0) {
                System.out.println("Stock invalido: no puede ser negativo. Se mantiene el valor anterior.");
            } else {
                prod.setStock(nuevoStock);
            }
        }

        listarCategorias();
        System.out.print("ID nueva categoria (Enter para mantener): ");
        String catStr = scanner.nextLine().trim();
        if (!catStr.isEmpty()) {
            Long catId = parseLong(catStr);
            if (catId == null) {
                System.out.println("ID invalido: se mantiene la categoria anterior.");
            } else {
                Optional<Categoria> catOpt = categoriaRepo.buscarPorId(catId);
                if (catOpt.isEmpty() || catOpt.get().isEliminado()) {
                    System.out.println("Categoria no encontrada o inactiva: se mantiene la anterior.");
                } else {
                    prod.setCategoria(catOpt.get());
                }
            }
        }

        productoRepo.guardar(prod);
        System.out.println("Producto actualizado correctamente.");
    }

    private static void listarProductos() {
        List<Producto> productos = productoRepo.listarActivos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos activos.");
            return;
        }
        System.out.println("\n-- Productos activos --");
        System.out.printf("%-5s | %-20s | %-15s | %-8s | %-10s%n",
                "ID", "NOMBRE", "CATEGORIA", "STOCK", "PRECIO");
        System.out.println("-".repeat(70));
        productos.forEach(p -> System.out.printf(
                "%-5d | %-20s | %-15s | %-8d | $%-10.2f%n",
                p.getId(), p.getNombre(),
                p.getCategoria() != null ? p.getCategoria().getNombre() : "-",
                p.getStock(), p.getPrecio()));
    }

    // =========================================================
    // REPORTES
    // =========================================================

    private static void menuReportes() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MENU REPORTES ---");
            System.out.println("1. Productos por categoria");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> productosPorCategoria();
                case "0" -> volver = true;
                default  -> System.out.println("Opcion invalida.");
            }
        }
    }

    private static void productosPorCategoria() {
        System.out.println("\n-- Productos por Categoria --");
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias activas.");
            return;
        }
        categorias.forEach(c -> System.out.printf("[ID: %-3d] %s%n", c.getId(), c.getNombre()));

        System.out.print("Ingrese el ID de la categoria: ");
        Long id = parseLong(scanner.nextLine().trim());
        if (id == null) { System.out.println("ERROR: ID invalido."); return; }

        Optional<Categoria> catOpt = categoriaRepo.buscarPorId(id);
        if (catOpt.isEmpty() || catOpt.get().isEliminado()) {
            System.out.println("ERROR: Categoria no encontrada.");
            return;
        }

        List<Producto> productos = productoRepo.buscarPorCategoria(id);
        if (productos.isEmpty()) {
            System.out.println("No hay productos activos en la categoria '"
                    + catOpt.get().getNombre() + "'.");
            return;
        }

        System.out.println("\nProductos de '" + catOpt.get().getNombre() + "':");
        productos.forEach(p -> System.out.printf(
                "[ID: %-3d] %-20s | $%-10.2f | Stock: %d%n",
                p.getId(), p.getNombre(), p.getPrecio(), p.getStock()));
    }

    // =========================================================
    // UTILIDADES DE PARSEO
    // =========================================================

    private static Long parseLong(String s) {
        try { return Long.parseLong(s); } catch (NumberFormatException e) { return null; }
    }

    private static Double parseDouble(String s) {
        try { return Double.parseDouble(s); } catch (NumberFormatException e) { return null; }
    }

    private static Integer parseInt(String s) {
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return null; }
    }
}
