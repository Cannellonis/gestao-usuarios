package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;

public class UsuarioEntityFactory {

    public static UsuarioEntity usuarioEntityValido() {
        return new UsuarioEntity()
                .toBuilder()
                .email("juanitomaneiro@gmail.com")
                .nome("Juanito Maneiro")
                .senha("juanito123").
                build();
    }
}
