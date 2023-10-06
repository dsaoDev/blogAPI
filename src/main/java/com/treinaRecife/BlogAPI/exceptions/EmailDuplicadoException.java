package com.treinaRecife.BlogAPI.exceptions;

public class EmailDuplicadoException extends RuntimeException{
    public EmailDuplicadoException(String message) {
        super(message);
    }
}
