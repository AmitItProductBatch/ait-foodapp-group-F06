package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException {

    private String errorMessage;

    private HttpStatus httpStatus;

    public CustomerException(String errorMessage, HttpStatus httpStatus) {

        this.errorMessage = errorMessage;

        this.httpStatus = httpStatus;
    }

    public CustomerException(HttpStatus httpStatus) {

        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public HttpStatus getHttpStatus() {

        return httpStatus;
    }
}