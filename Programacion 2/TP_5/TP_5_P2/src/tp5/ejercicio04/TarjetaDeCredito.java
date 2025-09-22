/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio04;

/**
 *
 * @author paddy
 */
public class TarjetaDeCredito {
    private String numero;
    private String fechaVencimiento;
    private Cliente cliente; // Asociación bidireccional
    private Banco banco; // Agregación

    public TarjetaDeCredito(String numero, String fechaVencimiento, Banco banco) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setCliente(Cliente cliente) {
        if (this.cliente != cliente) {
            this.cliente = cliente;
            if (cliente != null) {
                cliente.setTarjeta(this);
            }
        }
    }

    @Override
    public String toString() {
        String nombreCliente = (cliente != null) ? cliente.getNombre() : "N/A";
        return "TarjetaDeCredito [numero=" + numero + ", fechaVencimiento=" + fechaVencimiento +
               ", cliente=" + nombreCliente + ",\n  banco=" + banco.toString() + "]";
    }
}
