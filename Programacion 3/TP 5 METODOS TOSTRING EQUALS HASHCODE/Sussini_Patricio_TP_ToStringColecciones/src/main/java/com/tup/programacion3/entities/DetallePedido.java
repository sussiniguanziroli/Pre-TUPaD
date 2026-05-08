package com.tup.programacion3.entities;

import java.util.Objects;

public class DetallePedido {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido() {}

    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DetallePedido detalle = (DetallePedido) obj;
        return Objects.equals(producto, detalle.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto);
    }

    @Override
    public String toString() {
        return "DetallePedido[producto=" + producto.getNombre() + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
    }
}