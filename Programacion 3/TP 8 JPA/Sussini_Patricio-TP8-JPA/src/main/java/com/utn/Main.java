package com.utn;

import com.utn.dtos.UsuarioDTO;
import com.utn.entities.*;
import com.utn.enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        // =============================================
        // 4a. PERSISTIR 3 CATEGORIAS
        // =============================================
        System.out.println("\n========== PERSISTIENDO CATEGORIAS ==========");
        em.getTransaction().begin();

        Categoria cat1 = Categoria.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Electronica").descripcion("Dispositivos electronicos").build();
        Categoria cat2 = Categoria.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Ropa").descripcion("Indumentaria").build();
        Categoria cat3 = Categoria.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Alimentos").descripcion("Productos alimenticios").build();

        em.persist(cat1);
        em.persist(cat2);
        em.persist(cat3);

        em.getTransaction().commit();
        System.out.println("Categorias persistidas: " + cat1.getNombre() + ", " + cat2.getNombre() + ", " + cat3.getNombre());

        // =============================================
        // 4d. PERSISTIR 10 PRODUCTOS
        // =============================================
        System.out.println("\n========== PERSISTIENDO PRODUCTOS ==========");
        em.getTransaction().begin();

        Producto p1  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Notebook").precio(1500.0).descripcion("Laptop 15 pulgadas").stock(10).imagen("notebook.jpg").disponible(true).categoria(cat1).build();
        Producto p2  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Celular").precio(800.0).descripcion("Smartphone Android").stock(20).imagen("celular.jpg").disponible(true).categoria(cat1).build();
        Producto p3  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Remera").precio(25.0).descripcion("Remera algodon").stock(50).imagen("remera.jpg").disponible(true).categoria(cat2).build();
        Producto p4  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Pantalon").precio(45.0).descripcion("Jean azul").stock(30).imagen("pantalon.jpg").disponible(true).categoria(cat2).build();
        Producto p5  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Arroz").precio(2.5).descripcion("Arroz largo fino 1kg").stock(100).imagen("arroz.jpg").disponible(true).categoria(cat3).build();
        Producto p6  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Fideos").precio(1.8).descripcion("Fideos tallarin 500g").stock(80).imagen("fideos.jpg").disponible(true).categoria(cat3).build();
        Producto p7  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Teclado").precio(50.0).descripcion("Teclado mecanico").stock(15).imagen("teclado.jpg").disponible(true).categoria(cat1).build();
        Producto p8  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Mouse").precio(30.0).descripcion("Mouse inalambrico").stock(25).imagen("mouse.jpg").disponible(true).categoria(cat1).build();
        Producto p9  = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Campera").precio(90.0).descripcion("Campera invierno").stock(20).imagen("campera.jpg").disponible(true).categoria(cat2).build();
        Producto p10 = Producto.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Aceite").precio(3.2).descripcion("Aceite girasol 1L").stock(60).imagen("aceite.jpg").disponible(true).categoria(cat3).build();

        List<Producto> productos = new ArrayList<>(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        productos.forEach(em::persist);

        em.getTransaction().commit();
        System.out.println("10 productos persistidos.");

        // =============================================
        // 4b. PERSISTIR 3 PEDIDOS (2+ detalles c/u)
        // =============================================
        System.out.println("\n========== PERSISTIENDO PEDIDOS ==========");
        em.getTransaction().begin();

        Pedido pedido1 = Pedido.builder().eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.EFECTIVO).detalles(new ArrayList<>()).build();
        pedido1.addDetallePedido(2, p1);
        pedido1.addDetallePedido(1, p7);
        pedido1.calcularTotal();

        Pedido pedido2 = Pedido.builder().eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.CONFIRMADO).formaPago(FormaPago.TARJETA).detalles(new ArrayList<>()).build();
        pedido2.addDetallePedido(3, p3);
        pedido2.addDetallePedido(2, p4);
        pedido2.calcularTotal();

        Pedido pedido3 = Pedido.builder().eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.TERMINADO).formaPago(FormaPago.TRANSFERENCIA).detalles(new ArrayList<>()).build();
        pedido3.addDetallePedido(5, p5);
        pedido3.addDetallePedido(4, p6);
        pedido3.calcularTotal();

        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);

        em.getTransaction().commit();
        System.out.println("3 pedidos persistidos.");

        // =============================================
        // 4a. PERSISTIR 2 USUARIOS
        // =============================================
        System.out.println("\n========== PERSISTIENDO USUARIOS ==========");
        em.getTransaction().begin();

        Usuario usuario1 = Usuario.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Patricio").apellido("Sussini").mail("patricio@mail.com").celular("3794000001").contrasena("pass1234").rol(Rol.ADMIN).pedidos(new ArrayList<>(List.of(pedido1, pedido2))).build();
        Usuario usuario2 = Usuario.builder().eliminado(false).createdAt(LocalDateTime.now()).nombre("Juan").apellido("Perez").mail("juan@mail.com").celular("3794000002").contrasena("pass5678").rol(Rol.USUARIO).pedidos(new ArrayList<>(List.of(pedido3))).build();

        em.persist(usuario1);
        em.persist(usuario2);

        em.getTransaction().commit();
        System.out.println("2 usuarios persistidos: " + usuario1.getNombre() + ", " + usuario2.getNombre());

        // =============================================
        // 5. ACTUALIZAR 2 PRODUCTOS
        // =============================================
        System.out.println("\n========== ACTUALIZANDO PRODUCTOS ==========");
        em.getTransaction().begin();

        p1.setPrecio(1350.0);
        p1.setStock(8);
        em.merge(p1);

        p2.setPrecio(750.0);
        p2.setDisponible(false);
        em.merge(p2);

        em.getTransaction().commit();
        System.out.println("Producto actualizado: " + p1.getNombre() + " -> precio: $" + p1.getPrecio() + ", stock: " + p1.getStock());
        System.out.println("Producto actualizado: " + p2.getNombre() + " -> precio: $" + p2.getPrecio() + ", disponible: " + p2.getDisponible());

        // =============================================
        // 6. BUSCAR USUARIO POR ID
        // =============================================
        System.out.println("\n========== BUSCAR USUARIO POR ID ==========");
        Long idBuscar = usuario1.getId();
        Usuario usuarioPorId = em.find(Usuario.class, idBuscar);
        System.out.println("Usuario encontrado por id=" + idBuscar + ": " + usuarioPorId.getNombre() + " " + usuarioPorId.getApellido());

        // =============================================
        // 7. BUSCAR USUARIO POR MAIL
        // =============================================
        System.out.println("\n========== BUSCAR USUARIO POR MAIL ==========");
        String mailBuscar = "juan@mail.com";
        TypedQuery<Usuario> query = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.mail = :mail", Usuario.class);
        query.setParameter("mail", mailBuscar);
        Usuario usuarioPorMail = query.getSingleResult();
        System.out.println("Usuario encontrado por mail=" + mailBuscar + ": " + usuarioPorMail.getNombre() + " " + usuarioPorMail.getApellido());

        // =============================================
        // 8. BORRAR 1 PRODUCTO
        // =============================================
        System.out.println("\n========== BORRANDO PRODUCTO ==========");
        em.getTransaction().begin();
        Producto productoABorrar = em.find(Producto.class, p10.getId());
        em.remove(productoABorrar);
        em.getTransaction().commit();
        System.out.println("Producto eliminado: " + p10.getNombre());

        // =============================================
        // MOSTRAR DTO
        // =============================================
        System.out.println("\n========== USUARIO DTO (sin rol ni contrasena) ==========");
        UsuarioDTO dto = new UsuarioDTO(
            usuarioPorId.getId(), usuarioPorId.isEliminado(), usuarioPorId.getCreatedAt(),
            usuarioPorId.getNombre(), usuarioPorId.getApellido(), usuarioPorId.getMail(),
            usuarioPorId.getCelular(), usuarioPorId.getPedidos()
        );
        System.out.println(dto);

        em.close();
        emf.close();
        System.out.println("\n========== FIN ==========");
    }
}
