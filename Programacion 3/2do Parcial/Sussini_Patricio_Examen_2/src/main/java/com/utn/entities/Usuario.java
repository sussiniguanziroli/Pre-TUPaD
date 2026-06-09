package com.utn.entities;

import com.utn.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "usuarios")
public class Usuario extends Base {

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String mail;

    private String celular;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Pedido> pedidos;
}
