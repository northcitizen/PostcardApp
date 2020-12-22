package com.example.webapp.exception.postcard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
@Slf4j
public class PostcardExceptionHandler {

    @ExceptionHandler(value = {PostcardNotSavedException.class})
    public ResponseEntity<Object> handlePostcardNotSavedException(PostcardNotSavedException e) {
        return getHandledExceptionResponse(e, "postcard not saved");
    }

    @ExceptionHandler(value = {PostcardNotFoundException.class})
    public ResponseEntity<Object> handlePostcardNotFoundException(PostcardNotFoundException e) {
        return getHandledExceptionResponse(e, "postcard not found");
    }

    @ExceptionHandler(value = {PostcardConvertingException.class})
    public ResponseEntity<Object> handlePostcardNotConvertingException(PostcardConvertingException e) {
        return getHandledExceptionResponse(e, "converting error");
    }

    @ExceptionHandler(value = {PostcardNotUpdatedException.class})
    public ResponseEntity<Object> handlePostcardNotUpdatedException(PostcardNotUpdatedException e) {
        return getHandledExceptionResponse(e, "updating error");
    }

    private ResponseEntity<Object> getHandledExceptionResponse(Exception e, String message) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PostcardApiException postcardException = new PostcardApiException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error(message);
        return new ResponseEntity<>(postcardException, badRequest);
    }
}
