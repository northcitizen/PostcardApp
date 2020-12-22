package com.example.webapp.exception.user;

public class UserNotSavedException extends Exception {
    public UserNotSavedException() {
    }

    public UserNotSavedException(String message) {
        super(message);
    }

    public UserNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotSavedException(Throwable cause) {
        super(cause);
    }

    public UserNotSavedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
