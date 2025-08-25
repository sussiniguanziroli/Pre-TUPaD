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
public class Ejercicio9 {
public static double calcularCostoEnvio(double peso, String zona) {
        if (zona.equalsIgnoreCase("Nacional")) {
            return peso * 5;
        } else if (zona.equalsIgnoreCase("Internacional")) {
            return peso * 10;
        }
        return 0; 
    }

    public static double calcularTotalCompra(double precioProducto, double costoEnvio) {
        return precioProducto + costoEnvio;
    }

    public static void CostoEnvioYTotalCompra() {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el precio del producto: ");
        double precio = input.nextDouble();

        System.out.print("Ingrese el peso del paquete en kg: ");
        double peso = input.nextDouble();
        
        input.nextLine(); 

        System.out.print("Ingrese la zona de envío (Nacional o Internacional): ");
        String zona = input.nextLine();

        double costoEnvio = calcularCostoEnvio(peso, zona);
        double totalPagar = calcularTotalCompra(precio, costoEnvio);

        System.out.println("El costo de envío es: " + costoEnvio);
        System.out.println("El total a pagar es: " + totalPagar);
    }
}
