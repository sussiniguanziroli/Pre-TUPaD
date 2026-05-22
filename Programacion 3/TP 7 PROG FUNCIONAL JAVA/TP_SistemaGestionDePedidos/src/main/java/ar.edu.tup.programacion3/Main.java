package ar.edu.tup.programacion3;

import ar.edu.tup.programacion3.entities.*;
import ar.edu.tup.programacion3.enums.FormaPago;
import ar.edu.tup.programacion3.enums.Rol;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Producto p1 = new Producto("Pancho", 500.0, "Pancho clásico", 10, true);
        Producto p2 = new Producto("Bebida", 300.0, "Gaseosa 500ml", 3, true);
        Producto p3 = new Producto("Hamburguesa", 800.0, "Hamburguesa simple", 2, true);
        Producto p4 = new Producto("Papas fritas", 400.0, "Porción de papas", 0, false);

        List<Producto> productos = List.of(p1, p2, p3, p4);

        // 2) Productos disponibles
        System.out.println("=== Productos disponibles ===");
        productos.stream()
                .filter(Producto::getDisponible)
                .forEach(System.out::println);

        // 4) Productos con stock < 5
        System.out.println("\n=== Productos con stock menor a 5 ===");
        productos.stream()
                .filter(p -> p.getStock() < 5)
                .forEach(p -> System.out.println(p.getNombre() + " - stock: " + p.getStock()));

        // Pedido con detalles
        Pedido pedido = new Pedido(FormaPago.EFECTIVO);
        pedido.addDetallePedido(2, p1);
        pedido.addDetallePedido(2, p2);

        // 1) Calcular total
        pedido.calcularTotal();
        System.out.println("\n=== Total del pedido ===");
        System.out.println("Total: $" + pedido.getTotal());

        // 3) Cantidad de ítems
        System.out.println("\n=== Cantidad de ítems en el pedido ===");
        System.out.println("Total de ítems: " + pedido.getTotalItems());
    }
}