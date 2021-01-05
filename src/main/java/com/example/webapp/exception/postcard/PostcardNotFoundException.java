package com.example.webapp.exception.postcard;

import java.util.UUID;

public class PostcardNotFoundException extends Exception {

    public PostcardNotFoundException() {
    }

    public PostcardNotFoundException(String message) {
        super(message);
    }

    public PostcardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcardNotFoundException(Throwable cause) {
        super(cause);
    }

    public PostcardNotFoundException(UUID id) {
        super(String.format("postcard with Id %s not found", id));
    }

    public PostcardNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
