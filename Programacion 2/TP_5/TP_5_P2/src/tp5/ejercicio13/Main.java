/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio13;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Usuario usuario = new Usuario("Marcos Paz", "marcos.p@email.com");
        
        GeneradorQR generador = new GeneradorQR();
        
        System.out.println("--- Probando la Dependencia de Creacion ---");
        generador.generar("https://www.unsitioweb.com.ar", usuario);
    }
}