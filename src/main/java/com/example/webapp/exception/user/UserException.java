package com.example.webapp.exception.user;

import java.util.UUID;

public class UserException extends Exception {
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(UUID id) {
        super(String.format("address with id %s not found", id));
    }

    public UserException(String message) {
        super(message);
    }
}