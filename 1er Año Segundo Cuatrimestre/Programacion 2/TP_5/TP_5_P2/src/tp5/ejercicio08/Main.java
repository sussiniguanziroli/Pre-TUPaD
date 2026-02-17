/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio08;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Usuario usuario = new Usuario("Matias Gonzalez", "matias.g@email.com");

        Documento doc = new Documento("Contrato de Servicios", "Contenido del contrato...", 
                                      "a1b2c3d4e5f6", "2025-09-22", usuario);

        
        System.out.println("--- Detalle del Documento ---");
        System.out.println(doc.toString());

        System.out.println("\n--- Navegacion de las Relaciones ---");
        String nombreFirmante = doc.getFirmaDigital().getUsuario().getNombre();
        System.out.println("El documento '" + doc.getTitulo() + "' fue firmado por: " + nombreFirmante);
    }
}