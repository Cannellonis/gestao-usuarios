package com.cannellonis.gestaousuarios.presentation.dto;

import org.hibernate.validator.constraints.Length;

public record CadastrarUsuarioDto(
        @Length(min = 3, max = 100, message = "O comprimento do nome deve ser entre 3 e 100")
        String nome,
        @Length(min = 3, max = 100, message = "O comprimento do email deve ser entre 3 e 100")
        String email,
        @Length(min = 6, max = 100, message = "O comprimento da senha deve ser entre 6 e 100")
        String senha
) {
}
