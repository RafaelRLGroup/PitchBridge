package com.pitchbridge.api.controller.exceptions;

import com.pitchbridge.api.dto.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice // Indica que esta classe trata exceções globalmente
public class ResourceExceptionHandler {

    // 1. Tratador para "Recurso Não Encontrado" (Aquelas que a gente lançou com orElseThrow)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> entityNotFound(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Recurso não encontrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // 2. Tratador para Erros de Validação (O @Valid que a gente colocou)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Erro de validação");

        // Pega todas as mensagens que a gente escreveu nas entidades (Ex: "A doação mínima é...")
        String messages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.joining(", "));

        err.setMessage(messages);
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
