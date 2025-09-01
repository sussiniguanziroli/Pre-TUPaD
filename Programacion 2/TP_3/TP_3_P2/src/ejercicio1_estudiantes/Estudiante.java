/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1_estudiantes;

/**
 *
 * @author paddy
 */
public class Estudiante {

    String nombre;
    String apellido;
    String curso;
    double calificacion;

    //Constructor
    public Estudiante(String nombre, String apellido, String curso, double calificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.calificacion = calificacion;
    }

    //Metodos get y set
    public void mostrarInfo() {
        System.out.println("--- Informacion del Estudiante ---");
        System.out.println("Nombre: " + this.nombre + " " + this.apellido);
        System.out.println("Curso: " + this.curso);
        System.out.println("Calificacion Actual: " + this.calificacion);
    }

    public void subirCalificacion(double puntos) {
        this.calificacion = this.calificacion + puntos;
        System.out.println("Calificacion aumentada en " + puntos + " puntos.");
    }

    public void bajarCalificacion(double puntos) {
        this.calificacion = this.calificacion - puntos;
        System.out.println("Calificacion disminuida en " + puntos + " puntos.");
    }
}
