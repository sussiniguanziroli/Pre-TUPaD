package com.tp.jpa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Categoria extends Base {
    private String nombre;
    private String descripcion;
    @Builder.Default
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Producto> productos = new HashSet<>();

    public void addProducto(Producto producto) {
        this.productos.add(producto);
        producto.setCategoria(this);
    }
}
