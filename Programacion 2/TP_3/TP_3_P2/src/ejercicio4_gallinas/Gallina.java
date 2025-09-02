/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio4_gallinas;

/**
 *
 * @author paddy
 */
public class Gallina {
    private int idGallina;
    private int edad;
    private int huevosPuestos;
    
    //Constructor
    public Gallina(int idGallina, int edad) {
        this.idGallina = idGallina;
        this.edad = edad;
        this.huevosPuestos = 0;
    }
    
    public void ponerHuevo() {
        this.huevosPuestos = this.huevosPuestos + 1;
        System.out.println("La gallina " + this.idGallina + " puso un huevo!");
    }
    
    // Envejece un año la gallina
    public void envejecer() {
        this.edad = this.edad + 1;
    }
    
    public void mostrarEstado() {
        System.out.println("--- Estado de la Gallina ---");
        System.out.println("ID: " + this.idGallina);
        System.out.println("Edad: " + this.edad);
        System.out.println("Huevos Puestos: " + this.huevosPuestos);
        System.out.println("----------------------------");
    }
}
