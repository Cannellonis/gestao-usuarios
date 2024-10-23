package com.cannellonis.gestaousuarios.presentation.dto;

import java.time.LocalDateTime;

public record RespostaCadastrarUsuarioDto(
        String nome,
        String email,
        LocalDateTime criado,
        LocalDateTime atualizado
) {
}
