package com.example.webapp.exception.postcard;

import java.util.UUID;

public class PostcardConvertingException extends RuntimeException {
    public PostcardConvertingException() {
    }

    public PostcardConvertingException(UUID id) {
        super(String.format("postcard with Id %s not converted", id));
    }

    public PostcardConvertingException(String message) {
        super(message);
    }

    public PostcardConvertingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcardConvertingException(Throwable cause) {
        super(cause);
    }

    public PostcardConvertingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
