package com.treinaRecife.BlogAPI.handler;

import com.treinaRecife.BlogAPI.error.ErrorResponseForExceptions;
import com.treinaRecife.BlogAPI.exceptions.EntidadeNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(EntidadeNotFoundException.class)
    public ResponseEntity<ErrorResponseForExceptions> postNotFoundEx(EntidadeNotFoundException e, HttpServletRequest request){
        return handlingException(e, request, "Entidade não encontrada", HttpStatus.NOT_FOUND);
    }










    //Metodo auxiliar para tratar exceções
    private ResponseEntity<ErrorResponseForExceptions> handlingException(RuntimeException e, HttpServletRequest path, String error , HttpStatus status){
        ErrorResponseForExceptions err = new ErrorResponseForExceptions(status.value(), error, LocalDateTime.now(), path.getRequestURI(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
