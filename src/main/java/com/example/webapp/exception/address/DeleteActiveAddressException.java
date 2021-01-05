package com.example.webapp.exception.address;

import java.util.UUID;

public class DeleteActiveAddressException extends RuntimeException {
    public DeleteActiveAddressException() {
    }

    public DeleteActiveAddressException(String message) {
        super(message);
    }

    public DeleteActiveAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteActiveAddressException(Throwable cause) {
        super(cause);
    }

    public DeleteActiveAddressException(UUID id) {
        super(String.format("deleting address with id %s", id));
    }

    public DeleteActiveAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
