/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio5_naveEspacial;

/**
 *
 * @author paddy
 */
public class NaveEspacial {
    private String nombre;
    private int combustible;
    private final int MAX_COMBUSTIBLE = 100; // Limite maximo de combustible

    public NaveEspacial(String nombre, int combustibleInicial) {
        this.nombre = nombre;
        this.combustible = combustibleInicial;
    }

    public void despegar() {
        if (this.combustible >= 20) {
            // Despegar consume 20 unidades
            this.combustible -= 20; 
            System.out.println(this.nombre + " ha despegado! Queda combustible: " + this.combustible);
        } else {
            System.out.println("Fallo en el despegue. Combustible insuficiente.");
        }
    }

    public void avanzar(int distancia) {
        if (distancia <= this.combustible) {
            this.combustible -= distancia;
            System.out.println("La nave avanzo " + distancia + " unidades.");
        } else {
            System.out.println("No se puede avanzar. Combustible insuficiente para esa distancia.");
        }
    }

    public void recargarCombustible(int cantidad) {
        int combustiblePrevio = this.combustible;
        this.combustible += cantidad;
        if (this.combustible > MAX_COMBUSTIBLE) {
            this.combustible = MAX_COMBUSTIBLE;
        }
        System.out.println("Recarga completa. Se agregaron " + (this.combustible - combustiblePrevio) + " unidades.");
    }

    public void mostrarEstado() {
        System.out.println("--- Estado de la Nave ---");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Combustible: " + this.combustible + "/" + MAX_COMBUSTIBLE);
        System.out.println("-------------------------");
    }
}
