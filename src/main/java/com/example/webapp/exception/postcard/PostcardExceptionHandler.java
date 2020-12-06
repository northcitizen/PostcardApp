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
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PostcardException postcardException = new PostcardException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: postcard not saved");
        return new ResponseEntity<>(postcardException, badRequest);
    }

    @ExceptionHandler(value = {PostcardNotFoundException.class})
    public ResponseEntity<Object> handlePostcardNotFoundException(PostcardNotFoundException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PostcardException postcardException = new PostcardException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: postcard not found");
        return new ResponseEntity<>(postcardException, badRequest);
    }

    @ExceptionHandler(value = {PostcardNotUpdatedException.class})
    public ResponseEntity<Object> handlePostcardNotUpdatedException(PostcardNotUpdatedException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PostcardException postcardException = new PostcardException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        log.error("bad request: postcard not updated");
        return new ResponseEntity<>(postcardException, badRequest);
    }
}
