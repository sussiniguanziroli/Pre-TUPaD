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
public class Ejercicio6 {
    public static void ContadorPositivosNegativosCeros() {
        Scanner input = new Scanner(System.in);
        
        int positivos = 0;
        int negativos = 0;
        int ceros = 0;
        
        for (int i = 1; i <= 10; i++) {
            System.out.println("Ingrese el numero " + i + ": ");
            int numero = input.nextInt();
            
            if (numero > 0) {
                positivos++;
            } else if (numero < 0) {
                negativos++;
            } else {
                ceros++;
            }
        }
        
        System.out.println("Resultados:");
        System.out.println("Positivos: " + positivos);
        System.out.println("Negativos: " + negativos);
        System.out.println("Ceros: " + ceros);

    }
}
