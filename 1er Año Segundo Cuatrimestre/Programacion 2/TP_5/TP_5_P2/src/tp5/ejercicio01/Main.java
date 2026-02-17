/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio01;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Titular titular = new Titular("Juan Perez", "30123456");
        
        Pasaporte pasaporte = new Pasaporte("ARG123456", "05-10-2023", "rostro_juan.jpg", "JPG");

        titular.setPasaporte(pasaporte);

        System.out.println("--- PRUEBA DE METODOS toString() ---");
        System.out.println(titular);
        System.out.println(pasaporte);
    }
}
