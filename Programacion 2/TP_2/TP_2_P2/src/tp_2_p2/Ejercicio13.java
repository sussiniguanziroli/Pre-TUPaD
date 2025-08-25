/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_2_p2;

/**
 *
 * @author paddy
 */
public class Ejercicio13 {
    public static void imprimirPreciosRecursivo(double[] precios, int index) {
        if (index >= precios.length) {
            return;
        }
        
        System.out.println("Precio: $" + precios[index]);
        
        imprimirPreciosRecursivo(precios, index + 1);
    }

    public static void ImprimirArrayRecursivo() {
        double[] precios = {199.99, 299.5, 149.75, 399.0, 89.99};

        System.out.println("Precios originales:");
        imprimirPreciosRecursivo(precios, 0);

        precios[2] = 129.99;

        System.out.println("Precios modificados:");
        imprimirPreciosRecursivo(precios, 0);
    }
}
