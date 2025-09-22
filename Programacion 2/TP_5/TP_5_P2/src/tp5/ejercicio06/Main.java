/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio06;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Juan Doe", "3794-112233");
        Mesa mesa5 = new Mesa(5, 4);

        Reserva reserva = new Reserva("2025-10-15", "21:00", cliente1, mesa5);

        System.out.println("--- Detalle de la Reserva ---");
        System.out.println(reserva.toString());
        
        System.out.println("\n----------------------------------");
        
        System.out.println("El cliente '" + cliente1.getNombre() + "' y la mesa nro '" + mesa5.getNumero() + "' entidades separadas.");
    }
}