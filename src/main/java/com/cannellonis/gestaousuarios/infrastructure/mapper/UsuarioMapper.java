package com.cannellonis.gestaousuarios.infrastructure.mapper;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import com.cannellonis.gestaousuarios.presentation.dto.CadastrarUsuarioDto;
import com.cannellonis.gestaousuarios.presentation.dto.RespostaCadastroUsuarioDto;
import com.cannellonis.gestaousuarios.service.domain.UsuarioDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDomain entradaDtoToDomain(CadastrarUsuarioDto dadosUsuario);

    UsuarioEntity entradaDtoToEntity(CadastrarUsuarioDto dadosUsuario);

    RespostaCadastroUsuarioDto entityToRespostaDto(UsuarioEntity usuarioSalvo);
}
