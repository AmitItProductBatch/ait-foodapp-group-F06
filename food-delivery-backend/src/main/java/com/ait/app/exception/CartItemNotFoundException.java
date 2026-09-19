package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends RuntimeException {
	private String errorMessage;
	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public CartItemNotFoundException(String errorMessage, HttpStatus httpStatus) {

		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public CartItemNotFoundException(HttpStatus httpStatus) {

		this.httpStatus = httpStatus;
	}

}
