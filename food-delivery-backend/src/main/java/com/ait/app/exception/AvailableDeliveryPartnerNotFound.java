package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class AvailableDeliveryPartnerNotFound extends RuntimeException{
private String errorMessage;
private HttpStatus httpStatus;
public String getErrorMessage() {
	return errorMessage;
}
public HttpStatus getHttpStatus() {
	return httpStatus;
}
public AvailableDeliveryPartnerNotFound(String errorMessage, HttpStatus httpStatus) {
	
	this.errorMessage = errorMessage;
	this.httpStatus = httpStatus;
}
public AvailableDeliveryPartnerNotFound(HttpStatus httpStatus) {
	super();
	this.httpStatus = httpStatus;
}



}
