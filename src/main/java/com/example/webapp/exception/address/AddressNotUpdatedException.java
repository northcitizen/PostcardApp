package com.example.webapp.exception.address;

public class AddressNotUpdatedException extends RuntimeException {
    public AddressNotUpdatedException() {
    }

    public AddressNotUpdatedException(String message) {
        super(message);
    }

    public AddressNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotUpdatedException(Throwable cause) {
        super(cause);
    }

    public AddressNotUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
