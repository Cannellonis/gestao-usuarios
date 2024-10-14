package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;

public class CadastrarUsuarioDtoFactory {

    public static CadastrarUsuarioDto usuarioValido(){
        return new CadastrarUsuarioDto(
                "Felipinho Maneiro",
                "felipinhomaneiro@gmail.com"
                ,"123456789");
    }
    public static CadastrarUsuarioDto usuarioNomeInvalido(){
        return new CadastrarUsuarioDto(
                "",
                "felipinhomaneiro@gmail.com"
                ,"123456789");
    }
}
