
package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class RoleValidationException extends RuntimeException {

	private HttpStatus status;

	public RoleValidationException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}