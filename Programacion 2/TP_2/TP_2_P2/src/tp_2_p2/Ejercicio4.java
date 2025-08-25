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
public class Ejercicio4 {

    public static void CalculadoraDescuentoCat() {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el precio del producto: ");
        double precioOriginal = input.nextDouble();

        System.out.println("Ingrese la categoria del producto (A, B o C): ");
        char categoria = input.next().charAt(0);

        double porcentajeDescuento = 0;

        switch (categoria) {
            case 'a':
            case 'A':
                porcentajeDescuento = 10;
                break;
            case 'b':
            case 'B':
                porcentajeDescuento = 15;
                break;
            case 'c':
            case 'C':
                porcentajeDescuento = 20;
                break;
            default:
                System.out.println("Categoria no valida.");
                return;

        }

        double montoDescuento = precioOriginal * (porcentajeDescuento / 100.0);
        double precioFinal = precioOriginal - montoDescuento;

        System.out.println("Precio original: " + precioOriginal);
        System.out.println("Descuento aplicado: " + (int) porcentajeDescuento + "%");
        System.out.println("Precio final: " + precioFinal);
    }
}
