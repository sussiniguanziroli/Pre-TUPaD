/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp_4_p2;

/**
 *
 * @author paddy
 */
public class TP_4_P2 {

    public static void main(String[] args) {
        System.out.println("--- Creando empleados ---");
        Empleado emp1 = new Empleado(101, "Ana Gomez", "Desarrolladora Senior", 4500.0);
        Empleado emp2 = new Empleado("Juan Perez", "Analista de Datos");
        Empleado emp3 = new Empleado("Maria Rodriguez", "Gerente de Proyectos");

        System.out.println("Total de empleados creados: " + Empleado.mostrarTotalEmpleados());

        System.out.println("\n--- Informacion de los empleados ---");
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
        System.out.println(emp3.toString());

        System.out.println("\n--- Actualizando salarios ---");
        emp1.actualizarSalario(10.0); 
        System.out.println("Salario de Ana actualizado con 10% de aumento: " + emp1.getSalario());

        emp2.actualizarSalario(500); 
        System.out.println("Salario de Juan actualizado con aumento fijo de 500: " + emp2.getSalario());
        
        System.out.println("\n--- Informacion final de los empleados ---");
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
        System.out.println(emp3.toString());
    }
}
