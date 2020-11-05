package com.stones.stoneshomework.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Object> handleExceptions(ApplicationException ex) {
        return ResponseEntity.status(ex.httpStatus).body(ex.getMessage());
    }
}
