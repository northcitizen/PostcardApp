package com.example.webapp.exception.postcard;

public class PostcardNotSavedException extends RuntimeException {

    public PostcardNotSavedException() {
    }

    public PostcardNotSavedException(String message) {
        super(message);
    }

    public PostcardNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcardNotSavedException(Throwable cause) {
        super(cause);
    }

    public PostcardNotSavedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
