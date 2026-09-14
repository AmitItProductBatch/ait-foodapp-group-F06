package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class DuplicateRoleException extends RuntimeException {

	private String errorMessage;

	private HttpStatus httpStatus;

	public DuplicateRoleException(String errorMessage, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;

		this.httpStatus = httpStatus;
	}

	public String getErroeMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
