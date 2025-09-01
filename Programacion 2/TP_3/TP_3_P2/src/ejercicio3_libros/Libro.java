/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3_libros;

/**
 *
 * @author paddy
 */
public class Libro {

    private String titulo;
    private String autor;
    private int anioPublicacion;

    //Contructor
    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    //getters
    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAnioPublicacion() {
        return this.anioPublicacion;
    }

    //setters
    public void setAnioPublicacion(int nuevoAnio) {
        if (nuevoAnio > 2025) {
            System.out.println("Error: El anio " + nuevoAnio + " es invalido porque es un anio futuro.");
        } else {
            this.anioPublicacion = nuevoAnio;
            System.out.println("El anio de publicacion se actualizo correctamente a " + nuevoAnio + ".");
        }
    }

    public void MostrarInfo() {
        System.out.println("--- Ficha del Libro ---");
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Anio de Publicacion: " + this.anioPublicacion);
        System.out.println("-----------------------");
    }
}
