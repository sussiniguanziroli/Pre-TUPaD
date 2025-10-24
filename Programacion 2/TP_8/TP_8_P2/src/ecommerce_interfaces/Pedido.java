/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommerce_interfaces;

/**
 *
 * @author paddy
 */
import java.util.ArrayList;
import java.util.List;

class Pedido implements Pagable {
    private List<Producto> productos;
    private Cliente cliente;
    private String estado;

    public Pedido(Cliente cliente) {
        this.productos = new ArrayList<>();
        this.cliente = cliente;
        this.estado = "CREADO";
        notificarCliente();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }
    
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        notificarCliente();
    }
    
    private void notificarCliente() {
        String mensaje = "Su pedido esta en estado: " + this.estado;
        this.cliente.notificar(mensaje);
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularTotal();
        }
        return total;
    }
}
