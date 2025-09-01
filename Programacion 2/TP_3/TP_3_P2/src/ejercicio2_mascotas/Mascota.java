/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_mascotas;

/**
 *
 * @author paddy
 */
public class Mascota {
    String nombre;
    String especie;
    int edad;
    
    //Constructor
    public Mascota( String nombre, String especie,int edad){
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }
    
    public void MostrarInfo() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Especie: " + this.especie);
        System.out.println("Edad: " + this.edad);
    }
    
    public void cumplirAnios(int aniosTranscurridos) {
        if (aniosTranscurridos > 0) {
            edad = edad + aniosTranscurridos;
        }
    }
}
