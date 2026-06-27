package com.tp.jpa;

import com.tp.jpa.model.enums.EstadoPedido;
import com.tp.jpa.model.*;
import com.tp.jpa.model.enums.FormaPago;
import com.tp.jpa.model.enums.Rol;
import com.tp.jpa.repository.CategoriaRepository;
import com.tp.jpa.repository.PedidoRepository;
import com.tp.jpa.repository.ProductoRepository;
import com.tp.jpa.repository.UsuarioRepository;
import com.tp.jpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * Clase principal: menú de consola del sistema Food Store.
 * Orden de uso natural: Categorías -> Productos -> Usuarios -> Pedidos.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final CategoriaRepository categoriaRepo = new CategoriaRepository();
    private static final ProductoRepository productoRepo = new ProductoRepository();
    private static final UsuarioRepository usuarioRepo = new UsuarioRepository();
    private static final PedidoRepository pedidoRepo = new PedidoRepository();

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("===== FOOD STORE - MENÚ PRINCIPAL =====");
            System.out.println("1. Gestionar Categorías");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Usuarios");
            System.out.println("4. Gestionar Pedidos");
            System.out.println("5. Reportes");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1": menuCategorias(); break;
                case "2": menuProductos(); break;
                case "3": menuUsuarios(); break;
                case "4": menuPedidos(); break;
                case "5": menuReportes(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
        JPAUtil.close();
        System.out.println("Aplicación finalizada.");
    }

    // ── Submenús ─────────────────────────────────────────────────

    private static void menuCategorias() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Gestión de Categorías ---");
            System.out.println("1. Alta");
            System.out.println("2. Modificar");
            System.out.println("3. Baja lógica");
            System.out.println("4. Listado");
            System.out.println("0. Volver");
            switch (prompt("Opción: ")) {
                case "1": altaCategoria(); break;
                case "2": modificarCategoria(); break;
                case "3": bajaCategoria(); break;
                case "4": listarCategorias(); break;
                case "0": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuProductos() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Gestión de Productos ---");
            System.out.println("1. Alta");
            System.out.println("2. Modificar");
            System.out.println("3. Baja lógica");
            System.out.println("4. Listado");
            System.out.println("0. Volver");
            switch (prompt("Opción: ")) {
                case "1": altaProducto(); break;
                case "2": modificarProducto(); break;
                case "3": bajaProducto(); break;
                case "4": listarProductos(); break;
                case "0": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuUsuarios() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Gestión de Usuarios ---");
            System.out.println("1. Alta");
            System.out.println("2. Modificar");
            System.out.println("3. Baja lógica");
            System.out.println("4. Listado");
            System.out.println("5. Buscar por mail");
            System.out.println("0. Volver");
            switch (prompt("Opción: ")) {
                case "1": altaUsuario(); break;
                case "2": modificarUsuario(); break;
                case "3": bajaUsuario(); break;
                case "4": listarUsuarios(); break;
                case "5": buscarUsuarioPorMailMenu(); break;
                case "0": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuPedidos() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Gestión de Pedidos ---");
            System.out.println("1. Alta de pedido");
            System.out.println("2. Cambiar estado");
            System.out.println("3. Baja lógica");
            System.out.println("4. Listado");
            System.out.println("5. Pedidos por usuario");
            System.out.println("6. Pedidos por estado");
            System.out.println("0. Volver");
            switch (prompt("Opción: ")) {
                case "1": altaPedido(); break;
                case "2": cambiarEstadoPedido(); break;
                case "3": bajaPedido(); break;
                case "4": listarPedidos(); break;
                case "5": pedidosPorUsuarioMenu(); break;
                case "6": pedidosPorEstadoMenu(); break;
                case "0": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuReportes() {
        boolean volver = false;
        while (!volver) {
            System.out.println();
            System.out.println("--- Reportes ---");
            System.out.println("1. Productos por categoría");
            System.out.println("2. Pedidos por usuario");
            System.out.println("3. Pedidos por estado");
            System.out.println("4. Total facturado");
            System.out.println("0. Volver");
            switch (prompt("Opción: ")) {
                case "1": reporteProductosPorCategoria(); break;
                case "2": pedidosPorUsuarioMenu(); break;
                case "3": pedidosPorEstadoMenu(); break;
                case "4": reporteTotalFacturado(); break;
                case "0": volver = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // ── Categorías ───────────────────────────────────────────────

    private static void altaCategoria() {
        String nombre = promptNonVacio("Nombre: ");
        String descripcion = prompt("Descripción (opcional): ");
        Categoria categoria = Categoria.builder().nombre(nombre).descripcion(descripcion).build();
        categoria = categoriaRepo.guardar(categoria);
        System.out.println("Categoría creada con ID " + categoria.getId() + ".");
    }

    private static void modificarCategoria() {
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías activas.");
            return;
        }
        listarCategoriasEnPantalla(categorias);
        Long id = promptLong("ID de categoría a modificar: ");
        Categoria categoria = categorias.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        if (categoria == null) {
            System.out.println("No existe una categoría activa con ese ID.");
            return;
        }
        System.out.println("Valores actuales -> nombre: " + categoria.getNombre()
                + ", descripción: " + categoria.getDescripcion());

        String nombre = prompt("Nuevo nombre (Enter para mantener): ");
        if (!nombre.isEmpty()) categoria.setNombre(nombre);

        String descripcion = prompt("Nueva descripción (Enter para mantener): ");
        if (!descripcion.isEmpty()) categoria.setDescripcion(descripcion);

        categoriaRepo.guardar(categoria);
        System.out.println("Categoría actualizada.");
    }

    private static void bajaCategoria() {
        Long id = promptLong("ID de categoría a dar de baja: ");
        Optional<Categoria> categoriaOpt = categoriaRepo.buscarPorId(id);
        if (categoriaOpt.isEmpty() || categoriaOpt.get().isEliminado()) {
            System.out.println("No existe una categoría activa con ese ID.");
            return;
        }
        Categoria categoria = categoriaOpt.get();
        boolean ok = categoriaRepo.eliminarLogico(id);
        if (ok) {
            System.out.println("Categoría '" + categoria.getNombre() + "' dada de baja.");
        } else {
            System.out.println("No se pudo dar de baja la categoría.");
        }
    }

    private static void listarCategorias() {
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías activas.");
            return;
        }
        listarCategoriasEnPantalla(categorias);
    }

    private static void listarCategoriasEnPantalla(List<Categoria> categorias) {
        System.out.println("ID  | Nombre               | Descripción");
        for (Categoria c : categorias) {
            System.out.printf("%-4d| %-20s | %s%n", c.getId(), c.getNombre(), c.getDescripcion());
        }
    }

    // ── Productos ────────────────────────────────────────────────

    private static void altaProducto() {
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías activas. Cree una categoría antes de agregar productos.");
            return;
        }
        listarCategoriasEnPantalla(categorias);
        Long idCategoria = promptLong("ID de categoría: ");
        Categoria categoriaSeleccionada = categorias.stream()
                .filter(c -> c.getId().equals(idCategoria)).findFirst().orElse(null);
        if (categoriaSeleccionada == null) {
            System.out.println("La categoría seleccionada no es válida.");
            return;
        }

        String nombre = promptNonVacio("Nombre: ");
        String descripcion = prompt("Descripción: ");
        Double precio = promptDecimal("Precio: ");
        if (precio <= 0) {
            System.out.println("El precio debe ser mayor a 0. Operación cancelada.");
            return;
        }
        Integer stock = promptEntero("Stock: ");
        if (stock < 0) {
            System.out.println("El stock no puede ser negativo. Operación cancelada.");
            return;
        }
        String imagen = prompt("Imagen (URL, opcional): ");
        boolean disponible = promptSiNo("¿Disponible? (S/N) [S]: ", true);

        Producto producto = Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .imagen(imagen)
                .disponible(disponible)
                .build();

        // Producto no tiene campo propio hacia Categoria: la relación es
        // unidireccional desde Categoria (dueña del Set<Producto>). Por eso el
        // alta se hace agregando el producto a la colección administrada de la
        // categoría dentro de una única transacción, en vez de productoRepo.guardar().
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Categoria categoriaManaged = em.find(Categoria.class, idCategoria);
            categoriaManaged.addProducto(producto);
            tx.commit();
            System.out.println("Producto creado con ID " + producto.getId()
                    + " en la categoría '" + categoriaSeleccionada.getNombre() + "'.");
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            System.out.println("Error al guardar el producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    private static void modificarProducto() {
        List<Producto> productos = productoRepo.listarActivos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos activos.");
            return;
        }
        listarProductosEnPantalla(productos);
        Long id = promptLong("ID de producto a modificar: ");
        Producto producto = productos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (producto == null) {
            System.out.println("No existe un producto activo con ese ID.");
            return;
        }
        System.out.println("Valores actuales -> nombre: " + producto.getNombre()
                + ", precio: " + producto.getPrecio() + ", stock: " + producto.getStock());

        String nombre = prompt("Nuevo nombre (Enter para mantener): ");
        if (!nombre.isEmpty()) producto.setNombre(nombre);

        String precioStr = prompt("Nuevo precio (Enter para mantener): ");
        if (!precioStr.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioStr);
                if (precio > 0) producto.setPrecio(precio);
                else System.out.println("Precio inválido (debe ser > 0); se mantiene el anterior.");
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido; se mantiene el anterior.");
            }
        }

        String stockStr = prompt("Nuevo stock (Enter para mantener): ");
        if (!stockStr.isEmpty()) {
            try {
                int stock = Integer.parseInt(stockStr);
                if (stock >= 0) producto.setStock(stock);
                else System.out.println("Stock inválido (no puede ser negativo); se mantiene el anterior.");
            } catch (NumberFormatException e) {
                System.out.println("Stock inválido; se mantiene el anterior.");
            }
        }

        productoRepo.guardar(producto);
        System.out.println("Producto actualizado.");
    }

    private static void bajaProducto() {
        Long id = promptLong("ID de producto a dar de baja: ");
        Optional<Producto> productoOpt = productoRepo.buscarPorId(id);
        if (productoOpt.isEmpty() || productoOpt.get().isEliminado()) {
            System.out.println("No existe un producto activo con ese ID.");
            return;
        }
        Producto producto = productoOpt.get();
        boolean ok = productoRepo.eliminarLogico(id);
        if (ok) {
            System.out.println("Producto '" + producto.getNombre() + "' dado de baja.");
        } else {
            System.out.println("No se pudo dar de baja el producto.");
        }
    }

    private static void listarProductos() {
        List<Producto> productos = productoRepo.listarActivos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos activos.");
            return;
        }
        listarProductosEnPantalla(productos);
    }

    private static void listarProductosEnPantalla(List<Producto> productos) {
        System.out.println("ID  | Nombre               | Precio     | Stock | Disponible | Categoría");
        for (Producto p : productos) {
            String categoriaNombre = categoriaRepo.buscarCategoriaPorProducto(p.getId())
                    .map(Categoria::getNombre).orElse("-");
            System.out.printf("%-4d| %-20s | $%-9.2f | %-5d | %-10s | %s%n",
                    p.getId(), p.getNombre(), p.getPrecio(), p.getStock(),
                    Boolean.TRUE.equals(p.getDisponible()) ? "Sí" : "No", categoriaNombre);
        }
    }

    // ── Usuarios ─────────────────────────────────────────────────

    private static void altaUsuario() {
        String nombre = promptNonVacio("Nombre: ");
        String apellido = promptNonVacio("Apellido: ");
        String mail = promptNonVacio("Mail: ");
        if (usuarioRepo.buscarPorMail(mail).isPresent()) {
            System.out.println("Ya existe un usuario activo con ese mail.");
            return;
        }
        String celular = prompt("Celular (opcional): ");
        String contrasena = promptNonVacio("Contraseña: ");
        Rol rol = promptRol();

        Usuario usuario = Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .mail(mail)
                .celular(celular)
                .contraseña(contrasena)
                .rol(rol)
                .build();
        usuario = usuarioRepo.guardar(usuario);
        System.out.println("Usuario creado con ID " + usuario.getId() + ".");
    }

    private static void modificarUsuario() {
        List<Usuario> usuarios = usuarioRepo.listarActivos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios activos.");
            return;
        }
        listarUsuariosEnPantalla(usuarios);
        Long id = promptLong("ID de usuario a modificar: ");
        Usuario usuario = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        if (usuario == null) {
            System.out.println("No existe un usuario activo con ese ID.");
            return;
        }
        System.out.println("Valores actuales -> nombre: " + usuario.getNombre() + " " + usuario.getApellido()
                + ", mail: " + usuario.getMail() + ", celular: " + usuario.getCelular());

        String nombre = prompt("Nuevo nombre (Enter para mantener): ");
        if (!nombre.isEmpty()) usuario.setNombre(nombre);

        String apellido = prompt("Nuevo apellido (Enter para mantener): ");
        if (!apellido.isEmpty()) usuario.setApellido(apellido);

        String celular = prompt("Nuevo celular (Enter para mantener): ");
        if (!celular.isEmpty()) usuario.setCelular(celular);

        String contrasena = prompt("Nueva contraseña (Enter para mantener): ");
        if (!contrasena.isEmpty()) usuario.setContraseña(contrasena);

        String mail = prompt("Nuevo mail (Enter para mantener): ");
        if (!mail.isEmpty()) {
            Optional<Usuario> existente = usuarioRepo.buscarPorMail(mail);
            if (existente.isPresent() && !existente.get().getId().equals(usuario.getId())) {
                System.out.println("Ese mail ya está en uso por otro usuario. No se modifica el mail.");
            } else {
                usuario.setMail(mail);
            }
        }

        usuarioRepo.guardar(usuario);
        System.out.println("Usuario actualizado.");
    }

    private static void bajaUsuario() {
        Long id = promptLong("ID de usuario a dar de baja: ");
        Optional<Usuario> usuarioOpt = usuarioRepo.buscarPorId(id);
        if (usuarioOpt.isEmpty() || usuarioOpt.get().isEliminado()) {
            System.out.println("No existe un usuario activo con ese ID.");
            return;
        }
        Usuario usuario = usuarioOpt.get();
        boolean ok = usuarioRepo.eliminarLogico(id);
        if (ok) {
            System.out.println("Usuario '" + usuario.getNombre() + " " + usuario.getApellido()
                    + "' dado de baja. Sus pedidos permanecen en el sistema.");
        } else {
            System.out.println("No se pudo dar de baja el usuario.");
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioRepo.listarActivos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios activos.");
            return;
        }
        listarUsuariosEnPantalla(usuarios);
    }

    private static void listarUsuariosEnPantalla(List<Usuario> usuarios) {
        System.out.println("ID  | Nombre completo        | Mail                          | Rol");
        for (Usuario u : usuarios) {
            System.out.printf("%-4d| %-23s | %-29s | %s%n",
                    u.getId(), u.getNombre() + " " + u.getApellido(), u.getMail(), u.getRol());
        }
    }

    private static void buscarUsuarioPorMailMenu() {
        String mail = promptNonVacio("Mail a buscar: ");
        Optional<Usuario> usuarioOpt = usuarioRepo.buscarPorMail(mail);
        if (usuarioOpt.isEmpty()) {
            System.out.println("No existe un usuario activo con ese mail.");
            return;
        }
        Usuario u = usuarioOpt.get();
        System.out.println("ID: " + u.getId());
        System.out.println("Nombre: " + u.getNombre() + " " + u.getApellido());
        System.out.println("Mail: " + u.getMail());
        System.out.println("Celular: " + u.getCelular());
        System.out.println("Rol: " + u.getRol());
    }

    // ── Pedidos ──────────────────────────────────────────────────

    private static void altaPedido() {
        List<Usuario> usuarios = usuarioRepo.listarActivos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios activos. Cree un usuario antes de generar un pedido.");
            return;
        }
        listarUsuariosEnPantalla(usuarios);
        Long idUsuario = promptLong("ID de usuario para el pedido: ");
        Usuario usuarioSeleccionado = usuarios.stream()
                .filter(u -> u.getId().equals(idUsuario)).findFirst().orElse(null);
        if (usuarioSeleccionado == null) {
            System.out.println("No existe un usuario activo con ese ID. Operación cancelada.");
            return;
        }

        FormaPago formaPago = promptFormaPago();

        List<Producto> productosActivos = productoRepo.listarActivos();
        List<long[]> itemsTemp = new ArrayList<>(); // cada item: [idProducto, cantidad]
        boolean seguirAgregando = true;

        while (seguirAgregando) {
            if (productosActivos.isEmpty()) {
                System.out.println("No hay productos activos en el sistema.");
                break;
            }
            System.out.println("Catálogo de productos activos:");
            for (Producto p : productosActivos) {
                System.out.printf("  %d - %s | $%.2f | stock: %d | %s%n",
                        p.getId(), p.getNombre(), p.getPrecio(), p.getStock(),
                        Boolean.TRUE.equals(p.getDisponible()) ? "disponible" : "no disponible");
            }
            Long idProducto = promptLong("ID de producto a agregar: ");
            Producto producto = productosActivos.stream()
                    .filter(p -> p.getId().equals(idProducto)).findFirst().orElse(null);
            if (producto == null) {
                System.out.println("No existe un producto activo con ese ID.");
            } else if (!Boolean.TRUE.equals(producto.getDisponible())) {
                System.out.println("El producto no está disponible.");
            } else {
                int cantidad = promptEntero("Cantidad: ");
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                } else if (cantidad > producto.getStock()) {
                    System.out.println("Stock insuficiente. Stock disponible: " + producto.getStock());
                } else {
                    itemsTemp.add(new long[]{producto.getId(), cantidad});
                    // Se descuenta en la lista local para que, si se elige el mismo
                    // producto más de una vez, la validación de stock sea acumulativa.
                    producto.setStock(producto.getStock() - cantidad);
                    System.out.println("Producto agregado al pedido.");
                }
            }
            seguirAgregando = promptSiNo("¿Agregar otro producto? (S/N): ", false);
        }

        if (itemsTemp.isEmpty()) {
            System.out.println("El pedido debe tener al menos un detalle. Operación cancelada.");
            return;
        }

        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Usuario usuarioManaged = em.find(Usuario.class, idUsuario);
            Pedido pedido = Pedido.builder()
                    .fecha(LocalDate.now())
                    .estado(EstadoPedido.PENDIENTE)
                    .formaPago(formaPago)
                    .build();

            for (long[] item : itemsTemp) {
                Producto productoManaged = em.find(Producto.class, item[0]);
                int cantidad = (int) item[1];
                pedido.addDetallePedido(cantidad, productoManaged);
                productoManaged.setStock(productoManaged.getStock() - cantidad);
            }
            pedido.calcularTotal();
            // Pedido no tiene campo propio hacia Usuario: la relación es
            // unidireccional desde Usuario (dueño del Set<Pedido>). Por eso el
            // alta se hace agregando el pedido a la colección administrada del
            // usuario; el cascade = ALL persiste el Pedido y sus DetallePedido.
            usuarioManaged.addPedido(pedido);

            tx.commit();

            System.out.println("Pedido creado con ID " + pedido.getId() + ".");
            System.out.println("Usuario: " + usuarioSeleccionado.getNombre() + " " + usuarioSeleccionado.getApellido());
            System.out.println("Forma de pago: " + formaPago);
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Detalle:");
            for (DetallePedido d : pedido.getDetalles()) {
                System.out.printf("  %s x%d = $%.2f%n", d.getProducto().getNombre(), d.getCantidad(), d.getSubtotal());
            }
            System.out.printf("Total: $%.2f%n", pedido.getTotal());
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            System.out.println("Error al generar el pedido, se canceló la operación: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    private static void cambiarEstadoPedido() {
        Long id = promptLong("ID de pedido: ");
        Optional<Pedido> pedidoOpt = pedidoRepo.buscarPorId(id);
        if (pedidoOpt.isEmpty() || pedidoOpt.get().isEliminado()) {
            System.out.println("No existe un pedido activo con ese ID.");
            return;
        }
        Pedido pedido = pedidoOpt.get();
        System.out.println("Estado actual: " + pedido.getEstado());
        EstadoPedido nuevoEstado = promptEstadoPedido();
        pedido.setEstado(nuevoEstado);
        pedidoRepo.guardar(pedido);
        System.out.println("Pedido " + pedido.getId() + " actualizado a estado " + nuevoEstado + ".");
    }

    private static void bajaPedido() {
        Long id = promptLong("ID de pedido a dar de baja: ");
        Optional<Pedido> pedidoOpt = pedidoRepo.buscarPorId(id);
        if (pedidoOpt.isEmpty() || pedidoOpt.get().isEliminado()) {
            System.out.println("No existe un pedido activo con ese ID.");
            return;
        }
        Pedido pedido = pedidoOpt.get();
        boolean ok = pedidoRepo.eliminarLogico(id);
        if (ok) {
            System.out.printf("Pedido %d dado de baja. Total: $%.2f. El stock de los productos no se restaura.%n",
                    pedido.getId(), pedido.getTotal());
        } else {
            System.out.println("No se pudo dar de baja el pedido.");
        }
    }

    private static void listarPedidos() {
        List<Pedido> pedidos = pedidoRepo.listarActivos();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos activos.");
            return;
        }
        listarPedidosEnPantalla(pedidos);
    }

    private static void listarPedidosEnPantalla(List<Pedido> pedidos) {
        System.out.println("ID  | Fecha       | Estado      | Forma de pago  | Usuario                | Total");
        for (Pedido p : pedidos) {
            String nombreUsuario = usuarioRepo.buscarUsuarioPorPedido(p.getId())
                    .map(u -> u.getNombre() + " " + u.getApellido())
                    .orElse("-");
            System.out.printf("%-4d| %-11s | %-11s | %-14s | %-23s | $%.2f%n",
                    p.getId(), p.getFecha(), p.getEstado(), p.getFormaPago(), nombreUsuario, p.getTotal());
        }
    }

    private static void pedidosPorUsuarioMenu() {
        List<Usuario> usuarios = usuarioRepo.listarActivos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios activos.");
            return;
        }
        listarUsuariosEnPantalla(usuarios);
        Long idUsuario = promptLong("ID de usuario: ");
        boolean existe = usuarios.stream().anyMatch(u -> u.getId().equals(idUsuario));
        if (!existe) {
            System.out.println("No existe un usuario activo con ese ID.");
            return;
        }
        List<Pedido> pedidos = usuarioRepo.buscarPedidosPorUsuario(idUsuario);
        if (pedidos.isEmpty()) {
            System.out.println("El usuario no tiene pedidos.");
            return;
        }
        System.out.println("ID  | Fecha       | Estado      | Forma de pago  | Total");
        for (Pedido p : pedidos) {
            System.out.printf("%-4d| %-11s | %-11s | %-14s | $%.2f%n",
                    p.getId(), p.getFecha(), p.getEstado(), p.getFormaPago(), p.getTotal());
        }
    }

    private static void pedidosPorEstadoMenu() {
        EstadoPedido estado = promptEstadoPedido();
        List<Pedido> pedidos = pedidoRepo.buscarPorEstado(estado);
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos con estado " + estado + ".");
            return;
        }
        listarPedidosEnPantalla(pedidos);
    }

    // ── Reportes ─────────────────────────────────────────────────

    private static void reporteProductosPorCategoria() {
        List<Categoria> categorias = categoriaRepo.listarActivos();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías activas.");
            return;
        }
        listarCategoriasEnPantalla(categorias);
        Long idCategoria = promptLong("ID de categoría: ");
        boolean existe = categorias.stream().anyMatch(c -> c.getId().equals(idCategoria));
        if (!existe) {
            System.out.println("No existe una categoría activa con ese ID.");
            return;
        }
        List<Producto> productos = categoriaRepo.buscarProductosPorCategoria(idCategoria);
        if (productos.isEmpty()) {
            System.out.println("Esa categoría no tiene productos activos.");
            return;
        }
        System.out.println("ID  | Nombre               | Precio     | Stock");
        for (Producto p : productos) {
            System.out.printf("%-4d| %-20s | $%-9.2f | %d%n", p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
        }
    }

    private static void reporteTotalFacturado() {
        List<Pedido> terminados = pedidoRepo.buscarPorEstado(EstadoPedido.TERMINADO);
        double total = terminados.stream()
                .mapToDouble(p -> p.getTotal() != null ? p.getTotal() : 0.0)
                .sum();
        System.out.println("Total facturado: " + String.format(Locale.US, "$%.2f", total));
    }

    // ── Utilidades de entrada por consola ───────────────────────

    private static String prompt(String label) {
        System.out.print(label);
        return sc.nextLine().trim();
    }

    private static String promptNonVacio(String label) {
        String valor;
        do {
            valor = prompt(label);
            if (valor.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private static Integer promptEntero(String label) {
        while (true) {
            String valor = prompt(label);
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static Long promptLong(String label) {
        while (true) {
            String valor = prompt(label);
            try {
                return Long.parseLong(valor);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static Double promptDecimal(String label) {
        while (true) {
            String valor = prompt(label);
            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static boolean promptSiNo(String label, boolean porDefecto) {
        String valor = prompt(label).toUpperCase(Locale.ROOT);
        if (valor.isEmpty()) return porDefecto;
        return valor.startsWith("S");
    }

    private static Rol promptRol() {
        while (true) {
            String valor = prompt("Rol (ADMIN / USUARIO): ").toUpperCase(Locale.ROOT);
            try {
                return Rol.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Rol inválido. Opciones: ADMIN, USUARIO.");
            }
        }
    }

    private static FormaPago promptFormaPago() {
        while (true) {
            String valor = prompt("Forma de pago (TARJETA/TRANSFERENCIA/EFECTIVO): ").toUpperCase(Locale.ROOT);
            try {
                return FormaPago.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Forma de pago inválida.");
            }
        }
    }

    private static EstadoPedido promptEstadoPedido() {
        while (true) {
            String valor = prompt("Estado (PENDIENTE/CONFIRMADO/TERMINADO/CANCELADO): ").toUpperCase(Locale.ROOT);
            try {
                return EstadoPedido.valueOf(valor);
            } catch (IllegalArgumentException e) {
                System.out.println("Estado inválido.");
            }
        }
    }

}
