/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio03;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
   
        Autor autor = new Autor("Gabriel Garcia Marquez", "Colombiana");
        Editorial editorial = new Editorial("Sudamericana", "Buenos Aires");

    
        Libro libro = new Libro("Cien anios de soledad", "978-0307474728", autor, editorial);

   
        System.out.println("Informacion del Libro:");
        System.out.println(libro.toString());
    }
}
