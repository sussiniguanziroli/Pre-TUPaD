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
public class Ejercicio4 {
    
   public static void UsarScanner() {
    Scanner input = new Scanner(System.in);
    String nombre;
    int edad;

    System.out.print("Ingresa tu edad: ");
    edad = input.nextInt();
    input.nextLine(); 

    System.out.print("Ingresa tu nombre: ");
    nombre = input.nextLine();

    System.out.println("Mi nombre es " + nombre + " y tengo " + edad + " anios");
}
}
