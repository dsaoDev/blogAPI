package com.treinaRecife.BlogAPI.exceptions;

public class UniqueException extends RuntimeException{
    public UniqueException(String message) {
        super(message);
    }
}
