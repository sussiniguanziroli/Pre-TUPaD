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
public class Ejercicio5 {
    public static void SumaNumerosPares(){
        Scanner input = new Scanner(System.in);
        int sumaPares = 0;
        int numero;
        
        System.out.println("Ingrese un numero (0 para dejar de ingresar): ");
        numero = input.nextInt();
        
        while (numero != 0) {
            if (numero % 2 == 0) {
                sumaPares += numero;
            }
            System.out.println("Ingrese un numero (0 para dejar de ingresar): ");
            numero = input.nextInt();
        }
        
        System.out.println("La suma de los numeros pares es: " + sumaPares);
    }
}
