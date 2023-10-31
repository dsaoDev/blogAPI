package com.treinaRecife.BlogAPI.handler;

import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.error.ErrorResponseForValidations;
import com.treinaRecife.BlogAPI.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandler {

    //metodo Auxiliar para loopar os errors vindos da exception do Spring
    private static void loopErrors(MethodArgumentNotValidException e, Map<String, String> errors) {
        e.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
    }

    @ExceptionHandler(EntidadeNotFoundException.class)
    public ResponseEntity<ErrorResponseForExceptions> postNotFoundEx(EntidadeNotFoundException e, HttpServletRequest request) {
        return handlingException(e, request, "Entidade não encontrada", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseForValidations> validarCampos(MethodArgumentNotValidException e, HttpServletRequest request) {
        String path = request.getRequestURI();
        String error = "Erro nos campos abaixo";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();

        loopErrors(e, errors);

        ErrorResponseForValidations errorResponseForValidations = new ErrorResponseForValidations(status.value(), path, LocalDateTime.now(), error, errors);

        return ResponseEntity.status(status).body(errorResponseForValidations);
    }

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ErrorResponseForExceptions> emailDuplicadoEx(UniqueException e, HttpServletRequest request) {
        return handlingException(e, request, "Dados ja cadastrados no sistema", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReferenciaInvalidaException.class)
    public ResponseEntity<ErrorResponseForExceptions> referenciaInvalidaEx(ReferenciaInvalidaException e, HttpServletRequest request) {
        return handlingException(e, request, "Referencia invalida", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PaginaVaziaException.class)
    public ResponseEntity<ErrorResponseForExceptions> paginaVaziaEx(PaginaVaziaException e, HttpServletRequest request){
        return handlingException(e, request, "Pagina vazia", HttpStatus.OK );
    }



    @ExceptionHandler(CustomBadCredentialsException.class)
    public ResponseEntity<ErrorResponseForExceptions> authenticationEx(CustomBadCredentialsException e, HttpServletRequest request){
        return handlingException(e, request,"Erro na senha", HttpStatus.BAD_REQUEST);
    }

    //Metodo auxiliar para tratar exceções
    private ResponseEntity<ErrorResponseForExceptions> handlingException(RuntimeException e, HttpServletRequest path, String error, HttpStatus status) {
        ErrorResponseForExceptions err = new ErrorResponseForExceptions(status.value(), path.getRequestURI(), LocalDateTime.now(), error, e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
