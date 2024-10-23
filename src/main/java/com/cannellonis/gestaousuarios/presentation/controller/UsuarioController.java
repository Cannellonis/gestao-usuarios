package com.cannellonis.gestaousuarios.presentation.controller;

import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaCadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.service.CadastrarUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestao-usuario/v1/usuarios")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final CadastrarUsuarioService cadastrarUsuarioService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RespostaCadastrarUsuarioDto endpointCadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDto dadosUsuario) {

        return cadastrarUsuarioService.cadastrarUsuario(dadosUsuario);
    }
}
