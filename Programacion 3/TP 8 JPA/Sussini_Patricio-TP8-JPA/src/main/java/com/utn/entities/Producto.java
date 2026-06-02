package com.utn.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder
@Entity
@Table(name = "productos")
public class Producto extends Base {

    @EqualsAndHashCode.Include
    private String nombre;

    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private Boolean disponible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
