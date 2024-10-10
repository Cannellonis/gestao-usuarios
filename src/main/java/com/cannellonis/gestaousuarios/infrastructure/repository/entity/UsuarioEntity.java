package com.cannellonis.gestaousuarios.infrastructure.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Immutable
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    String id;
    String nome;
    String email;
    String senha;
    @UpdateTimestamp
    LocalDateTime criado;
    @CreationTimestamp
    LocalDateTime atualizado;

    @Generated
    public UsuarioEntity() {
        this.id = UUID.randomUUID().toString();
    }

    @Builder(toBuilder = true)
    public UsuarioEntity(
            String nome,
            String email,
            String senha,
            LocalDateTime atualizado,
            LocalDateTime criado
    ) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.criado = atualizado;
        this.atualizado = criado;
    }
}
