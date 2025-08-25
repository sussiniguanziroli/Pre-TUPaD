/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_2_p2;

import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class Ejercicio8 {
    public static double calcularPrecioFinal(double precioBase, double impuestoPorcentaje, double descuentoPorcentaje) {
        double impuesto = impuestoPorcentaje / 100.0;
        double descuento = descuentoPorcentaje / 100.0;
        
        double precioFinal = precioBase + (precioBase * impuesto) - (precioBase * descuento);
        return precioFinal;
    }
    public static void PrecioFinalImpuestoDescuento() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Ingrese el precio base del producto: ");
        double base = input.nextDouble();

        System.out.print("Ingrese el impuesto en porcentaje (10 para 10%): ");
        double impuesto = input.nextDouble();

        System.out.print("Ingrese el descuento en porcentaje (5 para 5%): ");
        double descuento = input.nextDouble();

        double precioFinal = calcularPrecioFinal(base, impuesto, descuento);

        System.out.println("El precio final del producto es: " + precioFinal);
        
    }
}
