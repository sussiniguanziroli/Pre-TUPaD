/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4_personalizada;

/**
 *
 * @author paddy
 */
public class EdadInvalidaException extends RuntimeException {

    // Constructor que recibe un mensaje
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
    
    // Constructor por defecto
    public EdadInvalidaException() {
        super("La edad esta fuera del rango valido.");
    }
}