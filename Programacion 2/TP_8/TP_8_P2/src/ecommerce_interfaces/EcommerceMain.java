/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommerce_interfaces;

/**
 *
 * @author paddy
 */
public class EcommerceMain {

    public static void main(String[] args) {
        
        Cliente ana = new Cliente("Ana");
        Pedido pedidoAna = new Pedido(ana);
        
        Producto lapiz = new Producto("Lapiz", 1.5);
        Producto cuaderno = new Producto("Cuaderno", 5.0);
        
        pedidoAna.agregarProducto(lapiz);
        pedidoAna.agregarProducto(cuaderno);

        double totalPedido = pedidoAna.calcularTotal();
        System.out.println("\nCalculando total del pedido:");
        System.out.println("Total a pagar (implementa Pagable): " + totalPedido); 
        
        TarjetaCredito tarjeta = new TarjetaCredito();
        
        if (tarjeta instanceof PagoConDescuento) {
            ((PagoConDescuento) tarjeta).aplicarDescuento(10.0);
        }
        
        tarjeta.procesarPago(totalPedido * 0.9);
        
        pedidoAna.cambiarEstado("ENVIADO");
        pedidoAna.cambiarEstado("ENTREGADO");
        
    }
}
