package com.cannellonis.gestaousuarios.service;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.infrastructure.security.TokenService;
import com.cannellonis.gestaousuarios.presentation.dto.LogarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaLogarUsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogarUsuarioService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public RespostaLogarUsuarioDto logarUsuario(LogarUsuarioDto dadosUsuario) {
        final UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(dadosUsuario.email(), dadosUsuario.senha());
        final Authentication autenticar = authenticationManager.authenticate(usuarioSenha);

        final var token = tokenService.gerarToken((UsuarioEntity) autenticar.getPrincipal());

        return new RespostaLogarUsuarioDto(token);
    }
}
