/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio05;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Propietario propietario = new Propietario("Ana Gómez", "28.987.654");

        Computadora pc = new Computadora("HP", "HP-156", "ASUS Prime A320MK", "AMD 3500");

        propietario.setComputadora(pc);

        System.out.println("--- Información Completa de la Computadora ---");
        System.out.println(pc.toString());

        System.out.println("\n--- Navegación de las Relaciones ---");
        System.out.println("El propietario de la PC " + pc.getMarca() + " es: " + pc.getPropietario().getNombre());
        System.out.println("La computadora de " + propietario.getNombre() + " tiene el número de serie: " + propietario.getComputadora().getNumeroSerie());
        System.out.println("El chipset de la placa madre de la PC es: " + pc.getPlacaMadre().getChipset());

    }
}
