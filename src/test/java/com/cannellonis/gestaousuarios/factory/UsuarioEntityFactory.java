package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.utils.CargoUsuario;
import java.time.LocalDateTime;

public class UsuarioEntityFactory {

    public static UsuarioEntity usuarioEntityValido() {
        return new UsuarioEntity()
                .toBuilder()
                .nome("Felipinho Maneiro")
                .email("felipinhomaneiro@gmail.com")
                .senha("123456789")
                .cargo(CargoUsuario.USUARIO)
                .criado(LocalDateTime.now())
                .atualizado(LocalDateTime.now())
                .build();
    }
}
