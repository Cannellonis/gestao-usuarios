package com.cannellonis.gestaousuarios.service;

import com.cannellonis.gestaousuarios.infrastructure.mapper.UsuarioMapper;
import com.cannellonis.gestaousuarios.infrastructure.repository.UsuarioRepository;
import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaCadastroUsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarUsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    public RespostaCadastroUsuarioDto cadastrarUsuario(CadastrarUsuarioDto dadosUsuario) {

        final UsuarioEntity salvarUsuario = usuarioMapper.entradaDtoToEntity(dadosUsuario);
        final UsuarioEntity usuarioSalvo = usuarioRepository.save(salvarUsuario);

        return usuarioMapper.entityToRespostaDto(usuarioSalvo);
    }

}
