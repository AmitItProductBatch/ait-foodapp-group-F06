package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class RestaurantAddressNotFoundException extends RuntimeException {

	private String errormessage;
	private HttpStatus httpStatus;
	public String getErrormessage() {
		return errormessage;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public RestaurantAddressNotFoundException(String errormessage, HttpStatus httpStatus) {
		super();
		this.errormessage = errormessage;
		this.httpStatus = httpStatus;
	}
	public RestaurantAddressNotFoundException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
	
	
	
}
