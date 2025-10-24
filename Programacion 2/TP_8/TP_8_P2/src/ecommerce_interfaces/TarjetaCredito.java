/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommerce_interfaces;

/**
 *
 * @author paddy
 */
class TarjetaCredito implements PagoConDescuento {
    
    @Override
    public void procesarPago(double monto) {
        System.out.println("Procesando pago con Tarjeta de Credito por " + monto);
    }
    
    @Override
    public void aplicarDescuento(double porcentaje) {
        System.out.println("Aplicando un " + porcentaje + "% de descuento a la tarjeta.");
    }
}
