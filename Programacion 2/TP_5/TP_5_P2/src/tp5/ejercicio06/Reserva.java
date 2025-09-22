/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio06;

/**
 *
 * @author paddy
 */
public class Reserva {
    private String fecha;
    private String hora;
    private Cliente cliente; 
    private Mesa mesa;       

    public Reserva(String fecha, String hora, Cliente cliente, Mesa mesa) {
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    @Override
    public String toString() {
        return "Reserva [Fecha=" + fecha + ", Hora=" + hora + 
               ",\n  " + cliente.toString() + 
               ",\n  " + mesa.toString() + "]";
    }
}