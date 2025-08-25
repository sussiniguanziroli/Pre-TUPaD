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
public class Ejercicio2 {
    public static void DeterminarMayorDeTres() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Ingrese el primer numero: ");
        int numero1 = input.nextInt();
        
        System.out.println("Ingrese el segundo numero: ");
        int numero2 = input.nextInt();
        
        System.out.println("Ingrese el tercer numero: ");
        int numero3 = input.nextInt();
        
        int mayor;
        
        if (numero1 >= numero2 && numero1 >= numero3) {
            mayor = numero1;
        } else if (numero2 >= numero1 && numero2 >= numero3) {
            mayor = numero2;
        } else {
            mayor = numero3;
        }
        
        System.out.println("El mayor de los tres es: " + mayor);
        
    }
}
