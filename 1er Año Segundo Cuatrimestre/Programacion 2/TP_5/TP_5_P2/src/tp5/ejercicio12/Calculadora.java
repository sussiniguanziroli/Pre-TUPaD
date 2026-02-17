/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio12;

/**
 *
 * @author paddy
 */
public class Calculadora {

    public void calcular(Impuesto impuesto) {
        System.out.println("Calculando impuesto para: " + impuesto.getContribuyente().getNombre());
        System.out.println("Monto a pagar: $" + impuesto.getMonto());
    }
}
