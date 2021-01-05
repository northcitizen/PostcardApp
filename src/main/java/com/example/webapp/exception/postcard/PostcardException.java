package com.example.webapp.exception.postcard;

public class PostcardException extends Exception {
    public PostcardException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostcardException(String message) {
        super(message);
    }
}
