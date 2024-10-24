package com.cannellonis.gestaousuarios.service.domain;

import com.cannellonis.gestaousuarios.utils.CargoUsuario;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDomain {
    String id;
    String nome;
    String email;
    String senha;
    CargoUsuario cargo;
    LocalDateTime criado;
    LocalDateTime atualizado;

    public UsuarioDomain(
            String id,
            String nome,
            String email,
            String senha,
            CargoUsuario cargo,
            LocalDateTime criado,
            LocalDateTime atualizado
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.criado = criado;
        this.atualizado = atualizado;
    }
}
