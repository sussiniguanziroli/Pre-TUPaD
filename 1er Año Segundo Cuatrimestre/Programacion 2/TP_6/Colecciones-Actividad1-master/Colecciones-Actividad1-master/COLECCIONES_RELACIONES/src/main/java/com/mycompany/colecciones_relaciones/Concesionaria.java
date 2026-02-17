
package com.mycompany.colecciones_relaciones;

import java.util.ArrayList;

public class Concesionaria {
    private String nombre;
    private ArrayList<Auto> autos;

    public Concesionaria(String nombre) {
        this.nombre = nombre;
        this.autos = new ArrayList<>();
    }
    public void agregarAuto(Auto a) {
        this.autos.add(a);
    }

    public void mostrarAutos() {
        if (autos.isEmpty()) {
            System.out.println("No hay autos");
        } else {
            for (int i = 0; i < autos.size(); i++) {
                System.out.println(autos.get(i));
            }
        }
    }
    
}
