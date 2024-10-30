package com.cannellonis.gestaousuarios.infrastructure.handler.exceptions;

public class UsuarioNaoEcontrado extends RuntimeException {
    public UsuarioNaoEcontrado(String message) {
        super(message);
    }
}
