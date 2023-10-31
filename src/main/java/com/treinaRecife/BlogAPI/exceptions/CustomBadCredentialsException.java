package com.treinaRecife.BlogAPI.exceptions;

public class CustomBadCredentialsException extends RuntimeException {

    public CustomBadCredentialsException(String message) {
        super(message);
    }
}
