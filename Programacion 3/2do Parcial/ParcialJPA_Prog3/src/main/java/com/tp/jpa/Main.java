package com.tp.jpa;

import com.tp.jpa.model.Categoria;
import com.tp.jpa.model.Producto;
import com.tp.jpa.repository.CategoriaRepository;
import com.tp.jpa.repository.ProductoRepository;
import com.tp.jpa.util.JPAUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        java.util.logging.LogManager.getLogManager().reset();
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        Scanner scanner = new Scanner(System.in);
        CategoriaRepository cRepo = new CategoriaRepository();
        ProductoRepository pRepo = new ProductoRepository();

        String menuPrincipal = ("""
                    +===============================================================+
                    |               SISTEMA DE GESTION DE PEDIDOS - JPA             |
                    +===============================================================+
                    
                    
                    =============================
                            MENU PRINCIPAL      
                    =============================
                    1. Gestion de Categorias
                    2. Gestion de Productos
                    3. Reportes
                    0. Salir
                    -----------------------------
                    Seleccione una opcion:\t""");

        String menuCategorias = ("""
                    =============================
                            MENU CATEGORIAS      
                    =============================
                    1. Alta de categoria
                    2. Baja de categoria
                    3. Modificar categoria
                    4. Listar categorias
                    0. Volver al menu principal
                    -----------------------------
                    Seleccione una opcion:\t""");
        String menuProductos = ("""
                    =============================
                            MENU PORODUCTOS      
                    =============================
                    1. Alta de producto
                    2. Baja de producto
                    3. Modificar producto
                    4. Listar productos
                    0. Volver al menu principal
                    -----------------------------
                    Seleccione una opcion:\t""");

        String menuReportes = ("""
                 =============================
                         MENU REPORTES      
                 =============================
                    1. Productos por Categoria (JPQL)
                    0. Volver al menu principal
                    -----------------------------
                    Seleccione una opcion:\t""");




        int opc = 0;
        do {
            System.out.print(menuPrincipal);
            try {
                opc = Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e){
                System.out.println("\nOpcion invalida...");
                opc = -1;
            }
            switch (opc) {
                // SUBMENU CATEGORIAS
                case 1: {
                    int opcCat = 0;
                    do {
                        System.out.print(menuCategorias);
                        try {
                            opcCat = Integer.parseInt(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("\nOpcion invalida.");
                            opcCat = -1;
                        }
                        switch (opcCat) {
                            case 1: {
                                System.out.println("\nIngrese el nombre de la nueva categoria: ");
                                String nombreCategoria = scanner.nextLine();
                                if (nombreCategoria.isEmpty()) {
                                    System.out.println("\nError: el nombre no puede estar vacio.");
                                    break;
                                }
                                System.out.println("\nIngrese la descripcion de la nueva categoria: ");
                                String descripcionCategoria = scanner.nextLine();
                                Categoria nuevaCategoria = Categoria.builder()
                                        .nombre(nombreCategoria)
                                        .descripcion(descripcionCategoria)
                                        .eliminado(false)
                                        .createdAt(LocalDateTime.now())
                                        .build();
                                nuevaCategoria = cRepo.guardar(nuevaCategoria);
                                System.out.println("\nCategoria creada con ID: " + nuevaCategoria.getId());
                                break;
                            }
                            case 2: {
                                listarCategorias(cRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID de la categoria a dar de baja: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Categoria> categoria = cRepo.buscarPorId(id);
                                if (categoria.isPresent() && !categoria.get().isEliminado()) {
                                    Categoria c = categoria.get();
                                    cRepo.eliminarLogico(id);
                                    System.out.println("\nCategoria eliminada: " + c.getNombre());
                                } else {
                                    //System.out.println("Error: el Id no existe.");
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                listarCategorias(cRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID de la categoria a modificar: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Categoria> categoria = cRepo.buscarPorId(id);
                                if (categoria.isPresent() && !categoria.get().isEliminado()) {
                                    Categoria c = categoria.get();
                                    System.out.println("\nNombre de categoria: " + c.getNombre() + "\nDescripcion de categoria: " + c.getDescripcion());
                                    System.out.println("\nIngrese el nuevo nombre de categoria: ");
                                    String nuevoNombre = scanner.nextLine();
                                    if (!nuevoNombre.isEmpty()) {
                                        c.setNombre(nuevoNombre);
                                    }
                                    System.out.println("\nIngrese la nueva descripcion de categoria: ");
                                    String nuevaDescripcion = scanner.nextLine();
                                    if (!nuevaDescripcion.isEmpty()) {
                                        c.setDescripcion(nuevaDescripcion);
                                    }
                                    cRepo.guardar(c);
                                } else {
                                    //System.out.println("Error: el Id no existe.");
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                listarCategorias(cRepo.listarActivos());
                                break;
                            }
                            case 0: {
                                break;
                            }
                            default: {
                                System.out.println("\nOpcion invalida...\nIntentelo nuevamente.");
                                break;
                            }
                        }
                    } while (opcCat != 0);
                    break;
                }
                // SUBMENU PRODUCTOS
                case 2: {
                    int opcProd = 0;
                    do {
                        System.out.print(menuProductos);
                        try{
                            opcProd = Integer.parseInt(scanner.nextLine().trim());
                        }
                        catch (NumberFormatException e){
                            System.out.println("\nOpcion invalida...");
                            opcProd = -1;
                        }
                        switch (opcProd) {
                            case 1: {
                                listarCategorias(cRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID de la categoria a seleccionar: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Categoria> categoria = cRepo.buscarPorId(id);
                                if (categoria.isPresent() && !categoria.get().isEliminado()) {
                                    Categoria categoriaElegida = categoria.get();
                                    System.out.println("\nIngrese el nombre del nuevo producto: ");
                                    String nombreProducto = scanner.nextLine();
                                    if (nombreProducto.isEmpty()) {
                                        System.out.println("\nError: el nombre no puede estar vacio.");
                                        break;
                                    }
                                    System.out.println("\nIngrese la descripcion del nuevo producto: ");
                                    String descripcionProducto = scanner.nextLine();
                                    System.out.println("\nIngrese el stock del nuevo producto: ");
                                    int stockProducto = Integer.parseInt(scanner.nextLine().trim());
                                    if (stockProducto < 0) {
                                        System.out.println("\nError: el stock no puede ser negativo.");
                                        break;
                                    }
                                    System.out.println("\nIngrese el precio del nuevo producto: ");
                                    Double precioProducto = Double.parseDouble(scanner.nextLine());
                                    if (precioProducto <= 0) {
                                        System.out.println("\nError: el precio debe ser mayor a 0.");
                                        break;
                                    }

                                    Producto nuevoProducto = Producto.builder()
                                            .nombre(nombreProducto)
                                            .descripcion(descripcionProducto)
                                            .eliminado(false)
                                            .createdAt(LocalDateTime.now())
                                            .stock(stockProducto)
                                            .precio(precioProducto)
                                            .categoria(categoriaElegida)
                                            .build();
                                    nuevoProducto = pRepo.guardar(nuevoProducto);
                                    System.out.println("\nProducto creado con ID: " + nuevoProducto.getId());
                                } else {
                                    //System.out.println("Error: el Id no existe.");
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                listarProductos(pRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID del producto a dar de baja: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Producto> producto = pRepo.buscarPorId(id);
                                if (producto.isPresent() && !producto.get().isEliminado()) {
                                    Producto p = producto.get();
                                    pRepo.eliminarLogico(id);
                                    System.out.println("\nProducto eliminado: " + p.getNombre());
                                } else {
                                    //System.out.println("Error: el Id no existe.");
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                listarProductos(pRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID del producto a modificar: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Producto> producto = pRepo.buscarPorId(id);
                                if (producto.isPresent() && !producto.get().isEliminado()) {
                                    Producto p = producto.get();
                                    System.out.println("\nNombre del producto: " + p.getNombre() + "\nDescripcion del producto: " + p.getDescripcion() + "\nCategoria del producto: " + p.getCategoria().getNombre() +
                                            "\nStock del producto: " + p.getStock() + "\nPrecio del producto: " + p.getPrecio());
                                    System.out.println("\nIngrese el nuevo nombre del producto: ");
                                    String nuevoNombre = scanner.nextLine();
                                    if (!nuevoNombre.isEmpty()) {
                                        p.setNombre(nuevoNombre);
                                    }
                                    System.out.println("\nIngrese la nuevo descripcion del producto: ");
                                    String nuevaDescripcion = scanner.nextLine();
                                    if (!nuevaDescripcion.isEmpty()) {
                                        p.setDescripcion(nuevaDescripcion);
                                    }
                                    System.out.println("\nIngrese el ID de la nueva categoria del producto: ");
                                    String nuevaCategoria = scanner.nextLine();
                                    if (!nuevaCategoria.isEmpty()) {
                                        try {
                                            Long nuevaCategoriaLong = Long.parseLong(nuevaCategoria.trim());
                                            Optional<Categoria> categoria = cRepo.buscarPorId(nuevaCategoriaLong);
                                            if (categoria.isPresent() && !categoria.get().isEliminado()) {
                                                p.setCategoria(categoria.get());
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("\nError: ingrese un numero valido.");
                                            break;
                                        }
                                    }
                                    System.out.println("\nIngrese el nuevo stock del producto: ");
                                    String nuevoStock = scanner.nextLine();
                                    if (!nuevoStock.isEmpty()) {
                                        int nuevoStockInt = Integer.parseInt(nuevoStock.trim());
                                        if (nuevoStockInt < 0) {
                                            System.out.println("\nError: el stock no puede ser negativo.");
                                            break;
                                        } else {
                                            p.setStock(nuevoStockInt);
                                        }
                                    }
                                    System.out.println("\nIngrese el nuevo precio del producto: ");
                                    String nuevoPrecio = scanner.nextLine();
                                    if (!nuevoPrecio.isEmpty()) {
                                        Double nuevoPrecioDouble = Double.parseDouble(nuevoPrecio);
                                        if (nuevoPrecioDouble <= 0) {
                                            System.out.println("\nError: el precio debe ser mayor a 0.");
                                            break;
                                        } else {
                                            p.setPrecio(nuevoPrecioDouble);
                                        }
                                    }
                                    pRepo.guardar(p);
                                } else {
                                    //System.out.println("Error: el Id no existe.");
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                listarProductos(pRepo.listarActivos());
                                break;
                            }
                            case 0: {
                                break;
                            }
                            default: {
                                System.out.println("\nOpcion invalida...\nIntentelo nuevamente.");
                                break;
                            }
                        }
                    } while (opcProd != 0);
                    break;
                }
                // SUBMENU REPORTES
                case 3: {
                    int opcRep = 0;
                    do {
                        System.out.print(menuReportes);
                        try{
                            opcRep = Integer.parseInt(scanner.nextLine().trim());
                        }
                        catch (NumberFormatException e){
                            System.out.println("\nOpcion invalida...");
                            opcRep = -1;
                        }
                        switch (opcRep) {
                            case 1: {
                                listarCategorias(cRepo.listarActivos());
                                Long id = 0L;
                                try {
                                    System.out.println("\nIngrese el ID de la categoria a seleccionar: ");
                                    id = Long.parseLong(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("\nError: ingrese un numero valido.");
                                    break;
                                }
                                Optional<Categoria> categoria = cRepo.buscarPorId(id);
                                if (categoria.isPresent() && !categoria.get().isEliminado()) {
                                    System.out.println("\nProductos activos en la categoria: " + categoria.get().getNombre());
                                    List<Producto> listadoProductos = pRepo.buscarPorCategoria(categoria.get().getId());
                                    if (!listadoProductos.isEmpty()){
                                        listarProductos(listadoProductos);
                                    }
                                    else{
                                        System.out.println("(No hay productos activos en esta categoria)");
                                    }
                                }
                                else {
                                    System.out.println("(No hay categorias activas con ID: " + id + ")");
                                }
                                break;
                            }
                            case 0: {
                                break;
                            }
                            default: {
                                System.out.println("\nOpcion invalida...\nIntentelo nuevamente.");
                                break;
                            }
                        }
                    }while (opcRep != 0) ;
                    break;
                }
                case 0: {
                    JPAUtil.close();
                    break;
                }
                default: {
                    System.out.println("\nOpcion invalida...\nIntentelo nuevamente: ");
                    break;
                }
            }
        }while (opc != 0);
    }


    public static void listarCategorias(List<Categoria> listadoCategorias){
        System.out.println(String.format("%-5s | %-20s | %-15s",
                "ID", "NOMBRE", "DESCRIPCION"));
        for (Categoria activo : listadoCategorias) {
            System.out.println(String.format("%-5s | %-20s | %-15s",
                    activo.getId(),
                    activo.getNombre(),
                    activo.getDescripcion()));
        }
    }

    public static void listarProductos(List<Producto> listadoProductos){
        System.out.println(String.format("%-5s | %-20s | %-15s | %-8s | %-10s",
                "ID", "NOMBRE", "CATEGORIA", "STOCK", "PRECIO"));
        for (Producto activo : listadoProductos) {
            System.out.println(String.format("%-5s | %-20s | %-15s | %-8s | %-10s",
                    activo.getId(),
                    activo.getNombre(),
                    activo.getCategoria().getNombre(),
                    activo.getStock(),
                    activo.getPrecio()));
        }
    }
}