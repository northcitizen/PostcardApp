package com.example.webapp.exception.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
@Slf4j
public class AddressExceptionHandler {

    @ExceptionHandler(value = {AddressNotFoundException.class})
    public ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException e) {
        return getHandledExceptionResponse(e, "address not found");
    }

    @ExceptionHandler(value = {DeleteActiveAddressException.class})
    public ResponseEntity<Object> handleLastAddressException(DeleteActiveAddressException e) {
        return getHandledExceptionResponse(e, "the current address");
    }

    @ExceptionHandler(value = {AddressConvertingException.class})
    public ResponseEntity<Object> handleAddressConvertingException(AddressConvertingException e) {
        return getHandledExceptionResponse(e, "converting error");
    }

    private ResponseEntity<Object> getHandledExceptionResponse(Exception e, String message) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        AddressApiException addressException = new AddressApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error(message);
        return new ResponseEntity<>(addressException, badRequest);
    }
}
