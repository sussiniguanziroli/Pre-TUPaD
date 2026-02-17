/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio09;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Paciente paciente = new Paciente("Lucia Fernandez", "OSDE 210");
        Profesional profesional = new Profesional("Dr. Roberto Carlos", "Cardiologia");

        CitaMedica cita = new CitaMedica("2025-11-10", "10:30", paciente, profesional);

        
        System.out.println("--- Detalle de la Cita Medica ---");
        System.out.println(cita.toString());

        System.out.println("\n--- Navegacion de las Relaciones ---");
        System.out.println("Paciente de la cita: " + cita.getPaciente().getNombre());
        System.out.println("Especialidad del profesional: " + cita.getProfesional().getEspecialidad());
    }
}