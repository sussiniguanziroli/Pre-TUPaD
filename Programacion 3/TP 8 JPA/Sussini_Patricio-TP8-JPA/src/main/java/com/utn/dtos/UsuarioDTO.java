package com.utn.dtos;

import com.utn.entities.Pedido;
import java.time.LocalDateTime;
import java.util.List;

public record UsuarioDTO(
    Long id,
    boolean eliminado,
    LocalDateTime createdAt,
    String nombre,
    String apellido,
    String mail,
    String celular,
    List<Pedido> pedidos
) {}
