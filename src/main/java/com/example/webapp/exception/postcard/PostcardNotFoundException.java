package com.example.webapp.exception.postcard;

public class PostcardNotFoundException extends RuntimeException {

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

    public PostcardNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
