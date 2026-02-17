/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio10;
import java.time.LocalDate;
/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        
        Titular titular = new Titular("Micaela Lopez", "35.111.222");

        CuentaBancaria cuenta = new CuentaBancaria("0011223344556677889900", 150000.0, 1234, LocalDate.now().toString());

        titular.setCuenta(cuenta);

        System.out.println("--- Detalle de la Cuenta ---");
        System.out.println(cuenta.toString());
        
        System.out.println("\n--- Navegacion de las Relaciones ---");
        System.out.println("El titular de la cuenta " + cuenta.getCbu() + " es: " + cuenta.getTitular().getNombre());
        System.out.println("La cuenta de " + titular.getNombre() + " tiene un saldo de: " + titular.getCuenta().getSaldo());
        System.out.println("La clave de la cuenta se modifico por ultima vez el: " + cuenta.getClave().getUltimaModificacion());
    }
}
