/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio12;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Contribuyente contribuyente = new Contribuyente("Empresa S.A.", "30-12345678-9");
        Impuesto impuestoGanancias = new Impuesto(125000.50, contribuyente);

        Calculadora calc = new Calculadora();
        
        System.out.println("--- Probando la Dependencia de Uso ---");
        calc.calcular(impuestoGanancias);
    }
}
