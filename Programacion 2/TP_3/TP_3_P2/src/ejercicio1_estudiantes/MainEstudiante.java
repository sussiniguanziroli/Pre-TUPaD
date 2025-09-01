/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_estudiantes;

/**
 *
 * @author paddy
 */
public class MainEstudiante {
    
    public static void main(String[] args) {
        System.out.println("Creando nuevo estudiante...");
        Estudiante est1 = new Estudiante("Juan", "Doe", "Programacion 2", 7.5);
        
        est1.mostrarInfo();
        
        System.out.println("\nRealizar cambios en la calificacion:");
        est1.subirCalificacion(2.2);
        est1.bajarCalificacion(5.5);
        
        System.out.println("Mostrar informacion final: ");
        est1.mostrarInfo();
    }
}
