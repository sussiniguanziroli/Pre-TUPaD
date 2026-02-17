/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio04;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco de la Nación Argentina", "30-99900001-3");
        Cliente cliente = new Cliente("Juan Pérez", "30.123.456");

        TarjetaDeCredito tarjeta = new TarjetaDeCredito("4500-1234-5678-9012", "12/29", banco);

        cliente.setTarjeta(tarjeta);

        System.out.println("--- Información Completa de la Tarjeta ---");
        System.out.println(tarjeta.toString());

        System.out.println("\n--- Relaciones ---");
        System.out.println("Desde el cliente podemos ver el número de su tarjeta: " + cliente.getTarjeta().getNumero());
        System.out.println("Desde la tarjeta podemos ver el nombre de su cliente: " + tarjeta.getCliente().getNombre());
        System.out.println("El banco emisor de la tarjeta de " + tarjeta.getCliente().getNombre() + " es: " + tarjeta.getBanco().getNombre());
    }
}