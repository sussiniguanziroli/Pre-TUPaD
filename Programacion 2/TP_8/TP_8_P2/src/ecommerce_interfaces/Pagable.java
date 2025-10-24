/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ecommerce_interfaces;

/**
 *
 * @author paddy
 */
public interface Pagable {
    double calcularTotal();
}

interface Pago {
    void procesarPago(double monto);
}

interface PagoConDescuento extends Pago {
    void aplicarDescuento(double porcentaje);
}

interface Notificable {
    void notificar(String mensaje);
}