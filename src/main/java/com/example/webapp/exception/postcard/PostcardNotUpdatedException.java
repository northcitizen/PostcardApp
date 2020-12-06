package com.example.webapp.exception.postcard;

public class PostcardNotUpdatedException extends RuntimeException {

    public PostcardNotUpdatedException() {
    }

    public PostcardNotUpdatedException(String message) {
        super(message);
    }

    public PostcardNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcardNotUpdatedException(Throwable cause) {
        super(cause);
    }

    public PostcardNotUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
