package com.stones.stoneshomework.exception;

import org.springframework.http.HttpStatus;

// TODO: to be extended with causing exception storage or human-readable message
public class ApplicationException extends RuntimeException {
    public final HttpStatus httpStatus;

    public ApplicationException(String message) {
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
