package com.tp.jpa.model;

import com.tp.jpa.model.interfaces.Calculable;
import com.tp.jpa.model.enums.Estado;
import com.tp.jpa.model.enums.FormaPago;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Pedido extends Base implements Calculable {
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private double total;
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    public void addDetallePedido(int cantidad, Producto producto){
        DetallePedido dp = DetallePedido.builder()
                //.id(0L) //Reemplazado por @GeneratedValue
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .cantidad(cantidad)
                .subtotal(producto.getPrecio() * cantidad)
                .producto(producto)
                .build();

        this.detallePedidos.add(dp);
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto){
        for (DetallePedido dp: this.detallePedidos) {
            if (dp.getProducto().equals(producto)) {
                return dp;
            }
        }
        return null;
    }
    public void deleteDetallePedidoByProducto(Producto producto){
        DetallePedido dp = findDetallePedidoByProducto(producto);
        if (dp != null){
            this.detallePedidos.remove(dp);
        }
    }

    @Override
    public void calcularTotal(){
        this.total = 0;
        for (DetallePedido dp: this.detallePedidos) {
            total += dp.getSubtotal();
        }
    }
}