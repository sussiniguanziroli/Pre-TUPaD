package com.utn;

import com.utn.dtos.UsuarioDTO;
import com.utn.entities.*;
import com.utn.enums.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Categoria cat1 = Categoria.builder().id(1L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Electrónica").descripcion("Dispositivos electrónicos").build();
        Categoria cat2 = Categoria.builder().id(2L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Ropa").descripcion("Indumentaria").build();
        Categoria cat3 = Categoria.builder().id(3L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Alimentos").descripcion("Productos alimenticios").build();

        Producto p1 = Producto.builder().id(1L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Notebook").precio(1500.0).descripcion("Laptop 15 pulgadas").stock(10).imagen("notebook.jpg").disponible(true).categoria(cat1).build();
        Producto p2 = Producto.builder().id(2L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Celular").precio(800.0).descripcion("Smartphone Android").stock(20).imagen("celular.jpg").disponible(true).categoria(cat1).build();
        Producto p3 = Producto.builder().id(3L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Remera").precio(25.0).descripcion("Remera algodón").stock(50).imagen("remera.jpg").disponible(true).categoria(cat2).build();
        Producto p4 = Producto.builder().id(4L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Pantalón").precio(45.0).descripcion("Jean azul").stock(30).imagen("pantalon.jpg").disponible(true).categoria(cat2).build();
        Producto p5 = Producto.builder().id(5L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Arroz").precio(2.5).descripcion("Arroz largo fino 1kg").stock(100).imagen("arroz.jpg").disponible(true).categoria(cat3).build();
        Producto p6 = Producto.builder().id(6L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Fideos").precio(1.8).descripcion("Fideos tallarín 500g").stock(80).imagen("fideos.jpg").disponible(true).categoria(cat3).build();
        Producto p7 = Producto.builder().id(7L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Teclado").precio(50.0).descripcion("Teclado mecánico").stock(15).imagen("teclado.jpg").disponible(true).categoria(cat1).build();
        Producto p8 = Producto.builder().id(8L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Mouse").precio(30.0).descripcion("Mouse inalámbrico").stock(25).imagen("mouse.jpg").disponible(true).categoria(cat1).build();
        Producto p9 = Producto.builder().id(9L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Campera").precio(90.0).descripcion("Campera invierno").stock(20).imagen("campera.jpg").disponible(true).categoria(cat2).build();
        Producto p10 = Producto.builder().id(10L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Aceite").precio(3.2).descripcion("Aceite girasol 1L").stock(60).imagen("aceite.jpg").disponible(true).categoria(cat3).build();

        List<Producto> productos = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        Pedido pedido1 = Pedido.builder().id(1L).eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.EFECTIVO).detalles(new java.util.ArrayList<>()).build();
        pedido1.addDetallePedido(2, p1);
        pedido1.addDetallePedido(1, p7);
        pedido1.calcularTotal();

        Pedido pedido2 = Pedido.builder().id(2L).eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.CONFIRMADO).formaPago(FormaPago.TARJETA).detalles(new java.util.ArrayList<>()).build();
        pedido2.addDetallePedido(3, p3);
        pedido2.addDetallePedido(2, p4);
        pedido2.calcularTotal();

        Pedido pedido3 = Pedido.builder().id(3L).eliminado(false).createdAt(LocalDateTime.now()).fecha(LocalDate.now()).estado(Estado.TERMINADO).formaPago(FormaPago.TRANSFERENCIA).detalles(new java.util.ArrayList<>()).build();
        pedido3.addDetallePedido(5, p5);
        pedido3.addDetallePedido(4, p6);
        pedido3.calcularTotal();

        Usuario usuario1 = Usuario.builder().id(1L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Patricio").apellido("Sussini").mail("patricio@mail.com").celular("3794000001").contraseña("pass1234").rol(Rol.ADMIN).pedidos(List.of(pedido1, pedido2, pedido3)).build();
        Usuario usuario2 = Usuario.builder().id(2L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Juan").apellido("Perez").mail("juan@mail.com").celular("3794000002").contraseña("pass5678").rol(Rol.USUARIO).pedidos(List.of(pedido2)).build();

        System.out.println("=== PRODUCTO ===");
        System.out.println(p1);

        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        productos.forEach(System.out::println);

        Usuario usuarioConMasPedidos = usuario1.getPedidos().size() >= usuario2.getPedidos().size() ? usuario1 : usuario2;
        System.out.println("\n=== PEDIDOS DE " + usuarioConMasPedidos.getNombre().toUpperCase() + " ===");
        usuarioConMasPedidos.getPedidos().forEach(System.out::println);

        Producto productoIgual = Producto.builder().id(99L).eliminado(false).createdAt(LocalDateTime.now()).nombre("Notebook").precio(999.0).descripcion("Otra descripción").stock(1).imagen("otra.jpg").disponible(false).categoria(cat2).build();
        System.out.println("\n=== COMPARACION equals ===");
        productos.forEach(p -> System.out.println("Notebook vs " + p.getNombre() + ": " + productoIgual.equals(p)));

        UsuarioDTO dto = new UsuarioDTO(usuario1.getId(), usuario1.isEliminado(), usuario1.getCreatedAt(), usuario1.getNombre(), usuario1.getApellido(), usuario1.getMail(), usuario1.getCelular(), usuario1.getPedidos());
        System.out.println("\n=== USUARIO DTO (sin rol ni contraseña) ===");
        System.out.println(dto);
    }
}