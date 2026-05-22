package com.utn.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DetallePedido {
    private int cantidad;
    private Double subtotal;
    private Producto producto;
}