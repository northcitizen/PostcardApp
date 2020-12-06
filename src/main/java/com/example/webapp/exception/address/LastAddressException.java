package com.example.webapp.exception.address;

public class LastAddressException extends RuntimeException {
    public LastAddressException() {
    }

    public LastAddressException(String message) {
        super(message);
    }

    public LastAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public LastAddressException(Throwable cause) {
        super(cause);
    }

    public LastAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
