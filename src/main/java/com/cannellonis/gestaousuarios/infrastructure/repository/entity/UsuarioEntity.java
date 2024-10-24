package com.cannellonis.gestaousuarios.infrastructure.repository.entity;

import com.cannellonis.gestaousuarios.utils.CargoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Immutable
@Table(name = "usuarios")
public class UsuarioEntity implements UserDetails {
    @Id
    String id;
    String nome;
    String email;
    String senha;
    CargoUsuario cargo;
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
            CargoUsuario cargo,
            LocalDateTime atualizado,
            LocalDateTime criado
    ) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.criado = atualizado;
        this.atualizado = criado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == CargoUsuario.ADMIN) {
            return List.of(new SimpleGrantedAuthority("CARGO_ADMIN"), new SimpleGrantedAuthority("CARGO_USUARIO"));
        } else {
            return List.of(new SimpleGrantedAuthority("CARGO_USUARIO"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
