package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class AddressException {
	private HttpStatus status;
	private String message;

	public AddressException(String message, HttpStatus status) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Object getMessage() {
		this.message = message;
		return message;
	}

	
}
