/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5_naveEspacial;

/**
 *
 * @author paddy
 */
public class MainNaveEspacial {

    public static void main(String[] args) {
        // Tarea: Crear una nave con 50 unidades de combustible
        NaveEspacial miNave = new NaveEspacial("Nave 11", 50);
        System.out.println("Nave creada. Estado inicial:");
        miNave.mostrarEstado();

        System.out.println("\nIntentando avanzar 80 unidades...");
        miNave.avanzar(80); // Falla
        miNave.mostrarEstado();

        System.out.println("\nIniciando recarga de 70 unidades...");
        miNave.recargarCombustible(70); // Deberia llegar al maximo de 100
        miNave.mostrarEstado();

        System.out.println("\nIntentando avanzar 60 unidades ahora...");
        miNave.avanzar(60); // Funciona

        System.out.println("\n--- ESTADO FINAL DE LA MISION ---");
        miNave.mostrarEstado();
    }
}
