/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_1_p2;

import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class Ejercicio8 {
    public static void DivisionJava(){
        Scanner input = new Scanner(System.in);
        int numero1, numero2;
        double division;
        
         System.out.println("Ingresa el primer numero: ");
         numero1 = Integer.parseInt(input.nextLine());
        
        
        System.out.println("Ingresa el segundo numero: ");
        numero2 = Integer.parseInt(input.nextLine());
        
        division = (numero1 / numero2);
        
        System.out.println("La division de ambos numeros con casting es: " + division);
        
    }
}
