/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4_personalizada;

/**
 *
 * @author paddy
 */
public class ManejadorEdad {

    public static void validarEdad(int edad) {

        if (edad < 0 || edad > 120) {
            throw new EdadInvalidaException("Edad " + edad + " no es valida. Debe estar entre 0 y 120.");
        }
        System.out.println("Edad " + edad + " es valida.");
    }

    public static void main(String[] args) {
        

        try {
            validarEdad(35);
        } catch (EdadInvalidaException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
        
        // Prueba con edad invalida
        try {
            validarEdad(-10);
        } catch (EdadInvalidaException e) {
            // Capturar la excepcion
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
        
        // Prueba con edad muy alta
        try {
            validarEdad(150);
        } catch (EdadInvalidaException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
    }
}