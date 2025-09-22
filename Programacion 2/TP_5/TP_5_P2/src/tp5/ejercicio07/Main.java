/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio07;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Motor motorDiesel = new Motor("TurboDiesel 2.8", "MTR-554433");
        Conductor conductor = new Conductor("Laura Rodriguez", "L-987654");

        Vehiculo camioneta = new Vehiculo("AF 123 CD", "Toyota Hilux SW4", motorDiesel);

        conductor.setVehiculo(camioneta);

        System.out.println("--- Información Completa del Vehículo ---");
        System.out.println(camioneta.toString());

        System.out.println("\n--- Relaciones ---");
        System.out.println("El conductor del vehículo " + camioneta.getPatente() + " es: " + camioneta.getConductor().getNombre());
        System.out.println("El vehículo de " + conductor.getNombre() + " es un: " + conductor.getVehiculo().getModelo());
        System.out.println("El tipo de motor del vehículo es: " + camioneta.getMotor().getTipo());
    }
}