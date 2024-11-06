package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.presentation.dto.LogarUsuarioDto;

public class LogarUsuarioDtoFactory {

    public static LogarUsuarioDto usuarioValido() {
        return new LogarUsuarioDto("felipinhomaneiro@gmail.com", "123456789");
    }

    public static LogarUsuarioDto usuarioEmailInvalido() {
        return new LogarUsuarioDto("albertinholegal@gmail.com", "123456789");
    }

    public static LogarUsuarioDto usuarioSenhaInvalida() {
        return new LogarUsuarioDto("felipinhomaneiro@gmail.com", "987654321");
    }

    public static LogarUsuarioDto usuarioFormatoEmailInvalido() {
        return new LogarUsuarioDto("@gmail.com", "987654321");
    }

    public static LogarUsuarioDto usuarioFormatoSenhaInvalida() {
        return new LogarUsuarioDto("felipinhomaneiro@gmail.com", "98765");
    }
}
