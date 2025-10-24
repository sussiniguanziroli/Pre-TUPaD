/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_division;
/**
 *
 * @author paddy
 */
import java.util.Scanner;

public class DivisionSegura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingrese el numerador: ");
            int numerador = scanner.nextInt();
            
            System.out.print("Ingrese el denominador: ");
            int denominador = scanner.nextInt();
            
            int resultado = numerador / denominador;
            System.out.println("Resultado: " + resultado);
            
        } catch (ArithmeticException e) {
            // Maneja el caso de division por cero
            System.out.println("Error: No es posible dividir por cero.");
        } catch (Exception e) {
            // Maneja cualquier otra excepcion
            System.out.println("Error: Ingrese solo numeros enteros.");
        } 
        

    }
}
