package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class CustomerAddressException extends RuntimeException {
	private String errorMessage;

    private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public CustomerAddressException(String errorMessage, HttpStatus httpStatus) {
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public CustomerAddressException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
    
}
