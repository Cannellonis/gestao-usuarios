package com.cannellonis.gestaousuarios.presentation.controller;

import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.LogarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaCadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaLogarUsuarioDto;
import com.cannellonis.gestaousuarios.service.CadastrarUsuarioService;
import com.cannellonis.gestaousuarios.service.LogarUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final LogarUsuarioService logarUsuarioService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RespostaCadastrarUsuarioDto endpointCadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDto dadosUsuario) {

        return cadastrarUsuarioService.cadastrarUsuario(dadosUsuario);
    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public RespostaLogarUsuarioDto endpointLogarUsuario(@RequestBody @Valid LogarUsuarioDto dadosUsuario) {

        final UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(dadosUsuario.email(), dadosUsuario.senha());
        final Authentication authenticate = authenticationManager.authenticate(usuarioSenha);

        return new RespostaLogarUsuarioDto(authenticate.getPrincipal().toString());
    }
}
