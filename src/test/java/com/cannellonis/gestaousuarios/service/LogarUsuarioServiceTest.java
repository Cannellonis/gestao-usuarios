package com.cannellonis.gestaousuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cannellonis.gestaousuarios.factory.LogarUsuarioDtoFactory;
import com.cannellonis.gestaousuarios.factory.UsuarioEntityFactory;
import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.infrastructure.security.TokenService;
import com.cannellonis.gestaousuarios.presentation.dto.LogarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaLogarUsuarioDto;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LogarUsuarioServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Spy
    private TokenService tokenService;
    @InjectMocks
    private LogarUsuarioService service;

    @BeforeEach
    void configuracao() throws NoSuchFieldException, IllegalAccessException {
        final Field segredo = TokenService.class.getDeclaredField("segredo");
        segredo.setAccessible(true);
        segredo.set(tokenService, "segredo");
    }

    @Test
    void deve_logar_usuario_corretamente() {
        final LogarUsuarioDto usuarioDtoMockValido = LogarUsuarioDtoFactory.usuarioValido();
        final UsuarioEntity usuarioEntityMockValido = UsuarioEntityFactory.
                usuarioEntityValido()
                .toBuilder()
                .senha("$2a$10$0wO27tvKY7g.htA/kw4KDOQ1rpQGxdQaZ60Tkw7T60kOcbSU0st5O")
                .build();
        final Authentication authenticationMock = mock(Authentication.class);

        final UsernamePasswordAuthenticationToken usuarioSenha =
                new UsernamePasswordAuthenticationToken(usuarioDtoMockValido.email(), usuarioDtoMockValido.senha());

        when(authenticationManager.authenticate(usuarioSenha)).thenReturn(authenticationMock);
        when(authenticationMock.getPrincipal()).thenReturn(usuarioEntityMockValido);

        final RespostaLogarUsuarioDto respostaService = service.logarUsuario(usuarioDtoMockValido);

        assertEquals(usuarioDtoMockValido.email(), tokenService.validarToken(respostaService.token()));
    }
}
