package com.cannellonis.gestaousuarios.factory;

import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;

public class CadastrarUsuarioDtoFactory {

    public static CadastrarUsuarioDto usuarioValido(){
        return new CadastrarUsuarioDto(
                "Felipinho Maneiro",
                "felipinhomaneiro@gmail.com"
                ,"123456789");
    }

    public static CadastrarUsuarioDto usuarioFormatoNomeInvalido(){
        return new CadastrarUsuarioDto(
                "fe",
                "felipinhomaneiro@gmail.com"
                ,"123456789");
    }

    public static CadastrarUsuarioDto usuarioFormatoEmailInvalido(){
        return new CadastrarUsuarioDto(
                "Felipinho Maneiro",
                "@gmail.com"
                ,"123456789");
    }

    public static CadastrarUsuarioDto usuarioFormatoSenhaInvalida(){
        return new CadastrarUsuarioDto(
                "Felipinho Maneiro",
                "felipinhomaneiro@gmail.com"
                ,"12345");
    }
}
