package com.treinaRecife.BlogAPI.exceptions;

public class PaginaVaziaException extends RuntimeException {

    public PaginaVaziaException(String message) {
        super(message);
    }
}
