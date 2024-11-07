package com.cannellonis.gestaousuarios.infrastructure.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.cannellonis.gestaousuarios.factory.UsuarioEntityFactory;
import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService service;

    @BeforeEach
    void configuracao() throws NoSuchFieldException, IllegalAccessException {
        final Field segredo = TokenService.class.getDeclaredField("segredo");
        segredo.setAccessible(true);
        segredo.set(service, "segredo");
    }

    @Nested
    class GerarToken {
        @Test
        void deve_gerar_token_com_sucesso() {
            final UsuarioEntity usuarioEntityMockValido = UsuarioEntityFactory.usuarioEntityValido();

            final String retornoService = service.gerarToken(usuarioEntityMockValido);

            assertEquals(usuarioEntityMockValido.getEmail(), JWT.decode(retornoService).getSubject());
        }

        @Test
        @Disabled("Sem conseguir alterar o atributo 'segredo' da classe TokenService para nulo, não é possível prosseguir com o teste")
        void deve_retornar_JWTCreationException_quando_segredo_for_nulo() {
            ReflectionTestUtils.setField(service, "segredo", null);
            final UsuarioEntity usuarioEntityMockValido = UsuarioEntityFactory.usuarioEntityValido();

            when(Algorithm.HMAC256("segredo")).thenReturn(null);

            assertThrows(
                    JWTCreationException.class,
                    () -> service.gerarToken(usuarioEntityMockValido),
                    "Erro ao gerar token"
            );
        }
    }

    @Nested
    class ValidarToken {

        @Test
        @Disabled("Teste não criado")
        void deve_validar_token_com_sucesso() {

        }

        @Test
        @Disabled("Teste não criado")
        void deve_retornar_JWTVerificationException_quando_segredo_for_diferente() {

        }
    }
}