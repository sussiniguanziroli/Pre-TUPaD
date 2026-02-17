
package com.mycompany.colecciones_relaciones;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Concesionaria c = new Concesionaria("FakeCar");

        c.mostrarAutos();

        c.agregarAuto(new Auto("123", "Rojo"));
        c.agregarAuto(new Auto("234", "Verde"));
        c.agregarAuto(new Auto("546", "Azul"));

        // c.getAutos().add(new Auto("123", "Rojo"));
        // c.getAutos().add(new Auto("234", "Verde"));
        // c.getAutos().add(new Auto("546", "Azul"));

        c.mostrarAutos();
    }
}
