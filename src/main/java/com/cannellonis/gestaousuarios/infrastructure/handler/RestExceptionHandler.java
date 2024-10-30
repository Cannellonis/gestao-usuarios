package com.cannellonis.gestaousuarios.infrastructure.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioJaPossuiCadastroException;
import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioNaoEcontrado;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

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
