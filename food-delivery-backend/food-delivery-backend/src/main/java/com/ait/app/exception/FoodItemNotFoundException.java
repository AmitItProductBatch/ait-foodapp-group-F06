package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class FoodItemNotFoundException extends RuntimeException {
private String errorMessage;
private HttpStatus httpStatus;
public String getErrorMessage() {
	return errorMessage;
}
public HttpStatus getHttpStatus() {
	return httpStatus;
}
public FoodItemNotFoundException(String errorMessage, HttpStatus httpStatus) {
	
	this.errorMessage = errorMessage;
	this.httpStatus = httpStatus;
}
public FoodItemNotFoundException(HttpStatus httpStatus) {
	
	this.httpStatus = httpStatus;
}

}
