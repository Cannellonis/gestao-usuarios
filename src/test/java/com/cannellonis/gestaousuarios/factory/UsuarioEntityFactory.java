package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;

public class UsuarioEntityFactory {

    public static UsuarioEntity usuarioEntityValido() {
        return new UsuarioEntity()
                .toBuilder()
                .nome("Felipinho Maneiro")
                .email("felipinhomaneiro@gmail.com")
                .senha("123456789").
                build();
    }
}
