package com.tp.jpa.model;

import com.tp.jpa.model.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "pedidos")
@EqualsAndHashCode(callSuper = true)
@Entity
public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pedido> pedidos = new HashSet<>();

    public void addPedido(Pedido pedido) { this.pedidos.add(pedido); }
}