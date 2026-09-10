package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.CustomerAddressException;
import com.ait.app.exception.CustomerException;

import com.ait.app.exception.CustomerProfileException;
import com.ait.app.exception.UpdateCustomerProfileException;

@ControllerAdvice
public class GlobalCustomerException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<>(customerException.getErrorMessage(), customerException.getHttpStatus());
	}
    @ExceptionHandler(CustomerAddressException.class)
	ResponseEntity<String> handleCustomerAddressException(CustomerAddressException customerAddressException) {

		return new ResponseEntity(customerAddressException.getErrorMessage(), customerAddressException.getHttpStatus());
	}
    ResponseEntity<String>handleCustomerProfileException(CustomerProfileException customerProfileException){
    	
    	return new ResponseEntity(customerProfileException.getErrorMessage(),customerProfileException.getHttpStatus());
    }
    ResponseEntity<String>handleUpdateCustomerProfileException(UpdateCustomerProfileException updateCustomerProfileException){
    	return new ResponseEntity(updateCustomerProfileException.getErrorMessage(),updateCustomerProfileException.getHttpStatus());
    }
	
	
	
}
