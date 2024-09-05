package com.cannellonis.gestaousuarios.infrastructure.repository;

import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
