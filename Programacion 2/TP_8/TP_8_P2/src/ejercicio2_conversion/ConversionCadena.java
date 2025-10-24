/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_conversion;

/**
 *
 * @author paddy
 */
import java.util.Scanner;

public class ConversionCadena {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingrese un numero como texto: ");
            String texto = scanner.nextLine();
            
     
            int numero = Integer.parseInt(texto);
            System.out.println("Conversion exitosa. Numero: " + numero);
            
        } catch (NumberFormatException e) {
     
            System.out.println("Error: El texto ingresado no es un numero entero valido.");
        }
        
     
    }
}