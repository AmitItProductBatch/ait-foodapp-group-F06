package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class FoodItemException {

	private String errorMessage;

	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public FoodItemException(String errorMessage, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;

		this.httpStatus = httpStatus;
	}

	
}
