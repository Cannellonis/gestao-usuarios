package com.cannellonis.gestaousuarios.infrastructure.handler;

import com.cannellonis.gestaousuarios.infrastructure.handler.exceptions.UsuarioJaPossuiCadastroException;
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
        return problemDetailBuilder(HttpStatus.BAD_REQUEST, "Erro ao cadastrar cliente", ex.getMessage());
    }
}
