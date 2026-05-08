package com.tup.programacion3;

import com.tup.programacion3.entities.*;
import com.tup.programacion3.enums.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Categoria cat1 = new Categoria(1L, "Electronica", "Productos electronicos");
        Categoria cat2 = new Categoria(2L, "Perifericos", "Accesorios de computadora");
        Categoria cat3 = new Categoria(3L, "Audio", "Equipos de sonido");

        Producto p1 = new Producto(1L, "Mouse Gamer", 15000.0, "Mouse RGB 6 botones", 50, "mouse.jpg", true);
        Producto p2 = new Producto(2L, "Teclado Mecanico", 35000.0, "Teclado switch blue", 30, "teclado.jpg", true);
        Producto p3 = new Producto(3L, "Monitor 24", 120000.0, "Monitor Full HD 144hz", 15, "monitor.jpg", true);
        Producto p4 = new Producto(4L, "Auriculares BT", 22000.0, "Auriculares inalambricos", 40, "auriculares.jpg", true);
        Producto p5 = new Producto(5L, "Webcam HD", 18000.0, "Camara 1080p", 25, "webcam.jpg", true);
        Producto p6 = new Producto(6L, "Laptop", 450000.0, "Laptop i5 8GB RAM", 10, "laptop.jpg", true);
        Producto p7 = new Producto(7L, "Tablet", 95000.0, "Tablet 10 pulgadas", 20, "tablet.jpg", true);
        Producto p8 = new Producto(8L, "Parlante BT", 12000.0, "Parlante portatil", 60, "parlante.jpg", true);
        Producto p9 = new Producto(9L, "Hub USB", 8000.0, "Hub 4 puertos USB 3.0", 80, "hub.jpg", true);
        Producto p10 = new Producto(10L, "Mousepad XL", 5000.0, "Mousepad 80x40cm", 100, "mousepad.jpg", true);

        cat1.addProducto(p3);
        cat1.addProducto(p6);
        cat1.addProducto(p7);
        cat2.addProducto(p1);
        cat2.addProducto(p2);
        cat2.addProducto(p9);
        cat2.addProducto(p10);
        cat3.addProducto(p4);
        cat3.addProducto(p5);
        cat3.addProducto(p8);

        Set<Producto> productos = new HashSet<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
        productos.add(p8);
        productos.add(p9);
        productos.add(p10);

        Pedido pedido1 = new Pedido(1L, LocalDate.now(), Estado.CONFIRMADO, FormaPago.TARJETA);
        pedido1.addDetallePedido(2, p1);
        pedido1.addDetallePedido(1, p2);
        pedido1.addDetallePedido(1, p3);

        Pedido pedido2 = new Pedido(2L, LocalDate.now(), Estado.PENDIENTE, FormaPago.EFECTIVO);
        pedido2.addDetallePedido(1, p6);
        pedido2.addDetallePedido(2, p9);

        Pedido pedido3 = new Pedido(3L, LocalDate.now(), Estado.TERMINADO, FormaPago.TRANSFERENCIA);
        pedido3.addDetallePedido(1, p4);
        pedido3.addDetallePedido(3, p10);
        pedido3.addDetallePedido(2, p8);

        Usuario u1 = new Usuario(1L, "Patricio", "Sussini", "patricio@mail.com", "3794000000", "pass123", Rol.ADMIN);
        u1.addPedido(pedido1);
        u1.addPedido(pedido2);
        u1.addPedido(pedido3);

        Usuario u2 = new Usuario(2L, "Juan", "Perez", "juan@mail.com", "3794111111", "pass456", Rol.USUARIO);
        Pedido pedido4 = new Pedido(4L, LocalDate.now(), Estado.PENDIENTE, FormaPago.TARJETA);
        pedido4.addDetallePedido(1, p7);
        pedido4.addDetallePedido(2, p5);
        u2.addPedido(pedido4);

        System.out.println("=== PRODUCTO INDIVIDUAL ===");
        System.out.println(p1);

        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        for (Producto p : productos) {
            System.out.println(p);
        }

        Usuario usuarioConMasPedidos = u1.getPedidos().size() >= u2.getPedidos().size() ? u1 : u2;
        System.out.println("\n=== PEDIDOS DEL USUARIO CON MAS PEDIDOS: " + usuarioConMasPedidos.getNombre() + " ===");
        for (Pedido p : usuarioConMasPedidos.getPedidos()) {
            System.out.println(p);
        }

        System.out.println("\n=== COMPARACION CON EQUALS ===");
        Producto productoNuevo = new Producto(99L, "Mouse Gamer", 15000.0, "Otra descripcion", 5, "otra.jpg", false);
        System.out.println("Producto nuevo: " + productoNuevo);
        System.out.println("¿Existe en la coleccion?");
        for (Producto p : productos) {
            if (p.equals(productoNuevo)) {
                System.out.println("IGUAL a: " + p);
            }
        }
        System.out.println("contains() resultado: " + productos.contains(productoNuevo));
    }
}