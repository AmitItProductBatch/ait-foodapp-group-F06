package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class RestaurantException extends RuntimeException {
private String errorMessage;
private HttpStatus httpStatus;
public String getErrorMessage() {
	return errorMessage;
}
public HttpStatus getHttpStatus() {
	return httpStatus;
}
public RestaurantException(String errorMessage, HttpStatus httpStatus) {
	
	this.errorMessage = errorMessage;
	this.httpStatus = httpStatus;
}
public RestaurantException(HttpStatus httpStatus) {
	
	this.httpStatus = httpStatus;
}


}
