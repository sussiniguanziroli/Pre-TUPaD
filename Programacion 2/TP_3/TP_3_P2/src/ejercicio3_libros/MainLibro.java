/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3_libros;

/**
 *
 * @author paddy
 */
public class MainLibro {

    public static void main(String[] args) {
        System.out.println("Creo un nuevo libro:");
        Libro l1 = new Libro("Cien Anios de Soledad", "Gabriel Garcia Marquez", 1967);

        l1.MostrarInfo();

        System.out.println("\nIntento modificar el anio con un valor INVALIDO (2030):");
        l1.setAnioPublicacion(2030);

        System.out.println("El anio del libro ahora es: " + l1.getAnioPublicacion());

        System.out.println("\nIntento modificar el anio con un valor VALIDO (1970):");
        l1.setAnioPublicacion(1970);

        System.out.println("\nMuestro la informacion final del libro:");
        l1.MostrarInfo();
    }
}
