package com.example.webapp.exception.user;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class UserException extends Exception {
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}