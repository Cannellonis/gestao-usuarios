package com.cannellonis.gestaousuarios.infrastructure.security;

import com.cannellonis.gestaousuarios.infrastructure.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final var token = this.recoverToken(request);

        if (token != null) {
            final String email = tokenService.validarToken(token);
            final UserDetails user = usuarioRepository.findByEmail(email);

            final var autenticacao = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        final var autHeader = request.getHeader("Authorization");

        if (autHeader == null || autHeader.isBlank() || !autHeader.startsWith("Bearer ")) {
            return null;
        }

        return autHeader.replace("Bearer ", "");
    }
}
