package com.cannellonis.gestaousuarios.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CadastrarUsuarioDto(
        @NotBlank
        @Length(min = 3, max = 100)
        String nome,
        @NotBlank
        @Length(min = 3, max = 100)
        String email,
        @NotBlank
        @Length(min = 6, max = 100)
        String senha
) {
}
