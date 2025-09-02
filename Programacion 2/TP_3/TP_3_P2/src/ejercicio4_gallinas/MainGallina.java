/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4_gallinas;

/**
 *
 * @author paddy
 */
public class MainGallina {

    public static void main(String[] args) {

        // Tarea: Crear dos gallinas
        System.out.println("Creando gallina 1...");
        Gallina gallina1 = new Gallina(101, 2);

        System.out.println("Creando gallina 2...");
        Gallina gallina2 = new Gallina(102, 1);

        System.out.println("\n--- ESTADO INICIAL ---");
        gallina1.mostrarEstado();
        gallina2.mostrarEstado();

        System.out.println("\n--- SIMULANDO ACCIONES ---");
        gallina1.ponerHuevo();
        gallina1.ponerHuevo();
        gallina1.envejecer();

        gallina2.ponerHuevo();
        gallina2.envejecer();
        gallina2.envejecer();

        System.out.println("\n--- ESTADO FINAL ---");
        gallina1.mostrarEstado();
        gallina2.mostrarEstado();

    }
}
