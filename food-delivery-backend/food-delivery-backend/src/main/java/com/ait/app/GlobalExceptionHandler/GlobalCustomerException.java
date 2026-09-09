package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.CustomerAddressException;
import com.ait.app.exception.CustomerException;

@ControllerAdvice
public class GlobalCustomerException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<>(customerException.getErrorMessage(), customerException.getHttpStatus());
	}

	ResponseEntity<String> handleCustomerAddressException(CustomerAddressException customerAddressException) {

		return new ResponseEntity(customerAddressException.getErrorMessage(), customerAddressException.getHttpStatus());
	}
}
