package com.cannellonis.gestaousuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.cannellonis.gestaousuarios.factory.CadastrarUsuarioDtoFactory;
import com.cannellonis.gestaousuarios.factory.UsuarioEntityFactory;
import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioJaPossuiCadastroException;
import com.cannellonis.gestaousuarios.infrastructure.mapper.UsuarioMapper;
import com.cannellonis.gestaousuarios.infrastructure.mapper.UsuarioMapperImpl;
import com.cannellonis.gestaousuarios.infrastructure.repository.UsuarioRepository;
import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaCadastrarUsuarioDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastrarUsuarioServiceTest {

    @Spy
    private UsuarioMapper mapper = new UsuarioMapperImpl();
    @Spy
    private UsuarioRepository repository;
    @InjectMocks
    private CadastrarUsuarioService service;
    @Captor
    private ArgumentCaptor<UsuarioEntity> usuarioEntityCaptor;

    @Test
    void deve_retornar_UsuarioJaPossuiCadastroException_ao_cadastrar_usuario_existente() {

        final CadastrarUsuarioDto usuarioDtoMockValido = CadastrarUsuarioDtoFactory.usuarioValido();
        when(repository.existsByEmail(usuarioDtoMockValido.email())).thenReturn(true);

        assertThrows(UsuarioJaPossuiCadastroException.class,
                () -> service.cadastrarUsuario(usuarioDtoMockValido),
                "Usuário com esse email já possui cadastro.");
    }

    @Test
    void deve_falhar_caso_os_dados_do_usuario_sofra_alteracoes_no_processo() {

        final CadastrarUsuarioDto usuarioDtoMockValido = CadastrarUsuarioDtoFactory.usuarioValido();
        final UsuarioEntity usuarioEntityMockValido = UsuarioEntityFactory.usuarioEntityValido();

        when(repository.existsByEmail(usuarioDtoMockValido.email())).thenReturn(false);
        doReturn(usuarioEntityMockValido).when(repository).save(usuarioEntityCaptor.capture());

        final RespostaCadastrarUsuarioDto respostaService = service.cadastrarUsuario(usuarioDtoMockValido);
        assertEquals(usuarioDtoMockValido.email(), respostaService.email());
        assertEquals(usuarioDtoMockValido.nome(), respostaService.nome());
    }
}