package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class DeliveryFeeRuleNotFoundException extends RuntimeException {
private String errormessage;
private HttpStatus httpStatus;
public String getErrormessage() {
	return errormessage;
}
public HttpStatus getHttpStatus() {
	return httpStatus;
}
public DeliveryFeeRuleNotFoundException(String errormessage, HttpStatus httpStatus) {
	super();
	this.errormessage = errormessage;
	this.httpStatus = httpStatus;
}
public DeliveryFeeRuleNotFoundException(HttpStatus httpStatus) {
	super();
	this.httpStatus = httpStatus;
}

}
