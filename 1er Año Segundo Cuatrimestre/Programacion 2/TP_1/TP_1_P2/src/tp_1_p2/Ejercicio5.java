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
public class Ejercicio5 {
    
    public static void OperacionesMat() {
        Scanner input = new Scanner(System.in);
        int numero1;
        int numero2;
        double suma;
        double multiplicacion;
        double resta;
        double division;
        
        System.out.println("Ingresa el primer numero: ");
        numero1 = input.nextInt();
        
        System.out.println("Ingresa el segundo numero: ");
        numero2 = input.nextInt();
        
        suma = numero1 + numero2;
        multiplicacion = numero1 * numero2;
        resta = numero1 - numero2;
        division = numero1 / numero2;
        
        System.out.println("La suma es: " + suma);
        System.out.println("La multiplicacion es: " + multiplicacion);
        System.out.println("La resta es: " + resta);
        System.out.println("La division es: " + division);
    }
    
}
