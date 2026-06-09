package com.tp.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class DetallePedido extends Base{
    private int cantidad;
    private double subtotal;
    @ManyToOne
    private Producto producto;
}