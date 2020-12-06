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

    @ExceptionHandler(value = {AddressNotSavedException.class})
    public ResponseEntity<Object> handleAddressNotSavedException(AddressNotSavedException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        AddressException addressException = new AddressException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: address not saved");
        return new ResponseEntity<>(addressException, badRequest);
    }

    @ExceptionHandler(value = {AddressNotFoundException.class})
    public ResponseEntity<Object> handleAddressNotFoundException(AddressNotFoundException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        AddressException addressException = new AddressException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: address not found");
        return new ResponseEntity<>(addressException, badRequest);
    }

    @ExceptionHandler(value = {AddressNotUpdatedException.class})
    public ResponseEntity<Object> handleAddressNotUpdatedException(AddressNotUpdatedException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        AddressException addressException = new AddressException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: address not updated");
        return new ResponseEntity<>(addressException, badRequest);
    }

    @ExceptionHandler(value = {LastAddressException.class})
    public ResponseEntity<Object> handleLastAddressException(LastAddressException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        AddressException addressException = new AddressException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: the current address");
        return new ResponseEntity<>(addressException, badRequest);
    }
}
