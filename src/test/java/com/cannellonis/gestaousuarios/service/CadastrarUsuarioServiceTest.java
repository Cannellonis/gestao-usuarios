package com.cannellonis.gestaousuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.cannellonis.gestaousuarios.service.domain.UsuarioDomain;
import com.cannellonis.gestaousuarios.utils.CargoUsuario;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CadastrarUsuarioServiceTest {

    @Spy
    private UsuarioMapper mapper = new UsuarioMapperImpl();
    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private CadastrarUsuarioService service;
    @Captor
    private ArgumentCaptor<UsuarioEntity> usuarioEntityCaptor;

    @Test
    void deve_cadastrar_usuario_corretamente(){
        final CadastrarUsuarioDto usuarioDtoMockValido = CadastrarUsuarioDtoFactory.usuarioValido();
        final UsuarioEntity usuarioEntityMockValido = UsuarioEntityFactory.usuarioEntityValido();
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        when(repository.existsByEmail(usuarioDtoMockValido.email())).thenReturn(false);
        when(repository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntityMockValido);

        final RespostaCadastrarUsuarioDto respostaService = service.cadastrarUsuario(usuarioDtoMockValido);

        verify(repository, times(1)).save(any(UsuarioEntity.class));
        verify(repository).save(usuarioEntityCaptor.capture());

        final UsuarioEntity usuarioPreSaveCapturado = usuarioEntityCaptor.getValue();

        assertEquals(usuarioDtoMockValido.email(), usuarioPreSaveCapturado.getEmail());
        assertEquals(usuarioDtoMockValido.nome(), usuarioPreSaveCapturado.getNome());
        assertEquals(CargoUsuario.USUARIO, usuarioPreSaveCapturado.getCargo());

        assertEquals(usuarioDtoMockValido.email(), respostaService.email());
        assertEquals(usuarioDtoMockValido.nome(), respostaService.nome());
        assertTrue(bCryptPasswordEncoder.matches(usuarioDtoMockValido.senha(), usuarioPreSaveCapturado.getSenha()));
        assertEquals(usuarioEntityMockValido.getCriado(), respostaService.criado());
        assertEquals(usuarioEntityMockValido.getAtualizado(), respostaService.atualizado());
    }

    @Test
    void deve_retornar_UsuarioJaPossuiCadastroException_ao_cadastrar_usuario_existente() {
        final CadastrarUsuarioDto usuarioDtoMockValido = CadastrarUsuarioDtoFactory.usuarioValido();

        when(repository.existsByEmail(usuarioDtoMockValido.email())).thenReturn(true);

        assertThrows(UsuarioJaPossuiCadastroException.class,
                () -> service.cadastrarUsuario(usuarioDtoMockValido),
                "Usuário com esse email já possui cadastro.");
    }

}
