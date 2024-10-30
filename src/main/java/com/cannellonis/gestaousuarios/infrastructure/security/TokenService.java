package com.cannellonis.gestaousuarios.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cannellonis.gestaousuarios.infrastructure.repository.entity.UsuarioEntity;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String segredo;

    public String gerarToken(UsuarioEntity usuario) {
        try {
            final Algorithm algoritimo = Algorithm.HMAC256(segredo);

            return JWT.create()
                    .withIssuer("${spring.application.name}")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Erro ao gerar token", e);
        }
    }

    public String validarToken(String token) {
        try {
            final Algorithm algoritimo = Algorithm.HMAC256(segredo);

            return JWT.require(algoritimo)
                    .withIssuer("${spring.application.name}")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("O token passado não é válido para essa requisição");
        }
    }

    private Instant gerarDataExpiracao() {
        return OffsetDateTime.now(ZoneOffset.of("-03:00"))
                .plusHours(2)
                .toInstant();
    }
}
