package com.example.webapp.exception;

public class UserNotUpdatedException extends RuntimeException {

    public UserNotUpdatedException() {
    }

    public UserNotUpdatedException(String message) {
        super(message);
    }

    public UserNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotUpdatedException(Throwable cause) {
        super(cause);
    }

    public UserNotUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
