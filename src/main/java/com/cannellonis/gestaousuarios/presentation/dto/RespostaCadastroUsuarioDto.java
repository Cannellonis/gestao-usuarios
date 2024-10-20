package com.cannellonis.gestaousuarios.presentation.dto;

import java.time.LocalDateTime;

public record RespostaCadastroUsuarioDto(
        String nome,
        String email,
        LocalDateTime criado,
        LocalDateTime atualizado
) {
}
