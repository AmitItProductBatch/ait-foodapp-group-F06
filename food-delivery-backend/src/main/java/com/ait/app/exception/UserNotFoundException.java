package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

private String errorMessage;
private HttpStatus httpStatus;


public UserNotFoundException(String errorMessage, HttpStatus httpStatus) {
	
	this.errorMessage = errorMessage;
	this.httpStatus = httpStatus;
}
public UserNotFoundException(HttpStatus httpStatus) {
	
	this.httpStatus = httpStatus;
}
public HttpStatus getHttpStatus() {
	return httpStatus;
}
public String getErrorMessage() {
	return errorMessage;
}

}
