package com.example.webapp.exception.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        return getHandledExceptionResponse(e, "user not found");
    }

    @ExceptionHandler(value = {UserConvertingException.class})
    public ResponseEntity<Object> handleUserConvertingException(UserConvertingException e) {
        return getHandledExceptionResponse(e, "converting error");
    }

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handleUserConvertingException(UserException e) {
        return getHandledExceptionResponse(e, "user exception");
    }

    private ResponseEntity<Object> getHandledExceptionResponse(Exception e, String message) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        UserApiException userException = new UserApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error(message);
        return new ResponseEntity<>(userException, badRequest);
    }
}
