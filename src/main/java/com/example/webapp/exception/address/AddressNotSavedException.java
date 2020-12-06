package com.example.webapp.exception.address;

public class AddressNotSavedException extends RuntimeException {
    public AddressNotSavedException() {
    }

    public AddressNotSavedException(String message) {
        super(message);
    }

    public AddressNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotSavedException(Throwable cause) {
        super(cause);
    }

    public AddressNotSavedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
