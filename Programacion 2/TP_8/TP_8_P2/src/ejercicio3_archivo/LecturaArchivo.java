/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3_archivo;

/**
 *
 * @author paddy
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecturaArchivo {

    // El metodo main declara la excepcion para ser manejada por la JVM,
    // o se usa try-catch para FileNotFoundException. Usaremos try-catch.
    public static void main(String[] args) {
        
        // Creamos un archivo que no existe para forzar la excepcion
        File archivo = new File("archivo_inexistente.txt");
        
        try {
            // Intentar crear un Scanner para leer el archivo
            Scanner lectorArchivo = new Scanner(archivo);
            
            while(lectorArchivo.hasNextLine()) {
                System.out.println(lectorArchivo.nextLine());
            }
            lectorArchivo.close();
            
        } catch (FileNotFoundException e) {
            // Maneja la excepcion si el archivo no se encuentra
            System.out.println("Error: El archivo no existe en la ruta especificada.");
            System.out.println("Detalles: " + e.getMessage());
        }
        
        System.out.println("Fin de la lectura del archivo.");
    }
}