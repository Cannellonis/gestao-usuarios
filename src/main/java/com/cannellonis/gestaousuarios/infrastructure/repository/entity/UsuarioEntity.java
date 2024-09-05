package com.cannellonis.gestaousuarios.infrastructure.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String nome;

    String email;

    String senha;

    LocalDateTime criado;

    @Generated
    public UsuarioEntity() {
    }

    @Builder(toBuilder = true)
    public UsuarioEntity(
            UUID id,
            String nome,
            String email,
            String senha
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.criado = LocalDateTime.now();
    }
}
