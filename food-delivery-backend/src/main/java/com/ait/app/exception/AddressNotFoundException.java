package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends RuntimeException {

	private HttpStatus status;

	public AddressNotFoundException(String message) {
		super(message);
		this.status = HttpStatus.NOT_FOUND;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
