package com.utn.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "categorias")
public class Categoria extends Base {

    private String nombre;
    private String descripcion;
}
