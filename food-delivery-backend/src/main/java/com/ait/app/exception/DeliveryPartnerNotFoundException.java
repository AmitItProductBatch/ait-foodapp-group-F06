package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class DeliveryPartnerNotFoundException extends RuntimeException {
	private String errorMessage;
	private HttpStatus httpStatus;

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public DeliveryPartnerNotFoundException(String errorMessage, HttpStatus httpStatus) {

		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public DeliveryPartnerNotFoundException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

}
