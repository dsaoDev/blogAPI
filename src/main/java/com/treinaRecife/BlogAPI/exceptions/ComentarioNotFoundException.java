package com.treinaRecife.BlogAPI.exceptions;

public class ComentarioNotFoundException extends RuntimeException {

    public ComentarioNotFoundException(String message) {
        super(message);
    }
}
