package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.CustomerAddressException;
import com.ait.app.exception.CustomerException;
import com.ait.app.exception.CustomerNotFoundException;

@ControllerAdvice
public class GlobalCustomerException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<>(customerException.getErrorMessage(), customerException.getHttpStatus());
	}

	ResponseEntity<String> handleCustomerAddressException(CustomerAddressException customerAddressException) {

		return new ResponseEntity(customerAddressException.getErrorMessage(), customerAddressException.getHttpStatus());
	}
	
	//
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
	}
	
}
