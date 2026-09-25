package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends RuntimeException {
    private String errorMessage;
	private HttpStatus status;
	public String getErrorMessage() {
		return errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public AddressNotFoundException(String errorMessage, HttpStatus status) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
	}
	public AddressNotFoundException(HttpStatus status) {
		super();
		this.status = status;
	}

	
}
