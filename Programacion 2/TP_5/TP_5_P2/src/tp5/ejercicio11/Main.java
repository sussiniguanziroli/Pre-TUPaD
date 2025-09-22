/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio11;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Artista artista = new Artista("Jose Larralde", "Folklore");
        Cancion cancion = new Cancion("Quimey Neuquen", artista);
        
        Reproductor miReproductor = new Reproductor();
        
        System.out.println("--- Probando Dependencia de Uso ---");
        miReproductor.reproducir(cancion);
    }
}
