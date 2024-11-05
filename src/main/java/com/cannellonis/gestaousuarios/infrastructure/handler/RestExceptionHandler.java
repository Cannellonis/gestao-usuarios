package com.cannellonis.gestaousuarios.infrastructure.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioJaPossuiCadastroException;
import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioNaoEcontrado;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ProblemDetail problemDetail = problemDetailBuilder(
                HttpStatus.CONFLICT, "Não foi possível acessar esse recurso", authException.getMessage()
        );

        problemDetail.setInstance(URI.create(request.getRequestURI()));

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), problemDetail);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ProblemDetail problemDetail = problemDetailBuilder(
                HttpStatus.CONFLICT, "Não foi possível acessar esse recurso", accessDeniedException.getMessage()
        );

        problemDetail.setInstance(URI.create(request.getRequestURI()));

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), problemDetail);
    }

    private ProblemDetail problemDetailBuilder(HttpStatus status, String title, String message) {
        final ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create("https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/" + status.value()));
        problemDetail.setTitle(title);
        problemDetail.setDetail(message);
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ProblemDetail erroAoValidarPayloadExceptionHandler(MethodArgumentNotValidException ex) {
        return problemDetailBuilder(HttpStatus.BAD_REQUEST, "Os dados no payload não são válidos", ex.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(UsuarioJaPossuiCadastroException.class)
    private ProblemDetail usuarioJaPossuiCadastroExceptionHandler(UsuarioJaPossuiCadastroException ex) {
        return problemDetailBuilder(HttpStatus.CONFLICT, "Erro ao cadastrar cliente", ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEcontrado.class)
    private ProblemDetail usuarioNaoEncontradoExceptionHandler(UsuarioNaoEcontrado ex) {
        return problemDetailBuilder(HttpStatus.NOT_FOUND, "Usuário com esse email não foi encontrado", ex.getMessage());
    }

    @ExceptionHandler(JWTCreationException.class)
    private ProblemDetail erroAoGerarTokenJWTExceptionHandler(JWTCreationException ex) {
        return problemDetailBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar token", ex.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    private ProblemDetail erroAoValidarTokenJWTExceptionHandler(JWTVerificationException ex) {
        return problemDetailBuilder(HttpStatus.UNAUTHORIZED, "Token inválido", ex.getMessage());
    }
}
