package com.cannellonis.gestaousuarios.presentation.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record LogarUsuarioDto(
        @Email
        String email,
        @Length(min = 6, max = 100, message = "O comprimento da senha deve ser entre 6 e 100")
        String senha
) {
}
