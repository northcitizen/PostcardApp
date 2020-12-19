package com.example.webapp.exception.user;

import java.util.UUID;

public class UserConvertingException extends RuntimeException {
    public UserConvertingException() {
    }

    public UserConvertingException(UUID id) {
        super(String.format("user with Id %s not converted", id));
    }

    public UserConvertingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserConvertingException(Throwable cause) {
        super(cause);
    }

    public UserConvertingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
