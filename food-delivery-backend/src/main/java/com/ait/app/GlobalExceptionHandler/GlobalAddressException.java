package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ait.app.exception.AddressException;
import com.ait.app.exception.CustomerException;

@RestControllerAdvice
public class GlobalAddressException {

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<String> handleAddressException(AddressException ex) {

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
;
	}
}