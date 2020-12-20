package com.example.webapp.exception.address;

import java.util.UUID;

public class AddressConvertingException extends Exception {
    public AddressConvertingException() {
    }

    public AddressConvertingException(UUID id) {
        super(String.format("address with Id %s not converted", id));
    }

    public AddressConvertingException(String message) {
        super(message);
    }

    public AddressConvertingException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressConvertingException(Throwable cause) {
        super(cause);
    }

    public AddressConvertingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
