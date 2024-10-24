package com.cannellonis.gestaousuarios.utils;

import lombok.Getter;

@Getter
public enum CargoUsuario {
    ADMIN("admin"),
    USUARIO("usuario");

    private final String cargo;

    CargoUsuario(String cargo) {
        this.cargo = cargo;
    }

}
