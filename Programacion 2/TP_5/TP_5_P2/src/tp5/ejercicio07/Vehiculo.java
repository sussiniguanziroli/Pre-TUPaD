/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio07;

/**
 *
 * @author paddy
 */
public class Vehiculo {
    private String patente;
    private String modelo;
    private Motor motor;
    private Conductor conductor; 

    public Vehiculo(String patente, String modelo, Motor motor) {
        this.patente = patente;
        this.modelo = modelo;
        this.motor = motor;
    }

    public String getPatente() {
        return patente;
    }

    public String getModelo() {
        return modelo;
    }

    public Motor getMotor() {
        return motor;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        if (this.conductor != conductor) {
            this.conductor = conductor;
            if (conductor != null) {
                conductor.setVehiculo(this);
            }
        }
    }

    @Override
    public String toString() {
        String nombreConductor = (conductor != null) ? conductor.getNombre() : "N/A";
        return "Vehiculo [patente=" + patente + ", modelo=" + modelo +
               ", conductor=" + nombreConductor + ",\n  " + motor.toString() + "]";
    }
}