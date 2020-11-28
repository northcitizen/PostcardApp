package com.example.webapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PostcardExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(PostcardExceptionHandler.class);

    @ExceptionHandler(value = {PostcardNotSavedException.class})
    public ResponseEntity<Object> handlePostcardException(PostcardNotSavedException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PostcardException postcardException = new PostcardException(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        logger.error("bad request");
        return new ResponseEntity<>(postcardException, badRequest);
    }
}
