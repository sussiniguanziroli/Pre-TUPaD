/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5_trywithreserouces;

/**
 *
 * @author paddy
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {
    
    public static void main(String[] args) {
        String rutaArchivo = "prueba.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            
            System.out.println("--- Contenido de prueba.txt ---");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            
        } catch (IOException e) {
            // Manejar IOException
            System.out.println("Error de lectura/escritura (IOException): " + e.getMessage());
            
        } finally {

            System.out.println("Finalizando operacion de lectura de archivo.");
        }
    }
}
