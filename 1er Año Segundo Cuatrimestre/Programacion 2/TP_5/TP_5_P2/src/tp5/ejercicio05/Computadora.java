/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio05;

/**
 *
 * @author paddy
 */
public class Computadora {
    private String marca;
    private String numeroSerie;
    private PlacaMadre placaMadre;
    private Propietario propietario;

    public Computadora(String marca, String numeroSerie, String modeloPlaca, String chipsetPlaca) {
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.placaMadre = new PlacaMadre(modeloPlaca, chipsetPlaca);
    }

    public String getMarca() {
        return marca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public PlacaMadre getPlacaMadre() {
        return placaMadre;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        if (this.propietario != propietario) {
            this.propietario = propietario;
            if (propietario != null) {
                propietario.setComputadora(this);
            }
        }
    }
    
    @Override
    public String toString() {
        String nombrePropietario = (propietario != null) ? propietario.getNombre() : "N/A";
        return "Computadora [marca=" + marca + ", numeroSerie=" + numeroSerie + 
               ", propietario=" + nombrePropietario + ",\n  " + placaMadre.toString() + "]";
    }
}
