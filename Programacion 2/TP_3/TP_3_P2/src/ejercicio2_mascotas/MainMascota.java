/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_mascotas;

/**
 *
 * @author paddy
 */
public class MainMascota {
    
    public static void main(String[] args) {
        System.out.println("Creo una nueva mascota: ");
        Mascota m1 = new  Mascota("Bartolo", "Perro", 5);
        
        System.out.println("Muestro la m1 con los primeros valores con el metodo MostrarInfo();");
        m1.MostrarInfo();
        
        System.out.println("Pasamos 2 anios: ");
        m1.cumplirAnios(2);
        m1.MostrarInfo();
    }
}
