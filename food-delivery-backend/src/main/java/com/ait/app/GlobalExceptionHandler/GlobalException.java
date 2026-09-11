package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.CustomerAddressException;
import com.ait.app.exception.CustomerException;

import com.ait.app.exception.CustomerProfileException;
import com.ait.app.exception.DuplicateRoleException;
import com.ait.app.exception.RestaurantException;
import com.ait.app.exception.RoleValidationException;
import com.ait.app.exception.UpdateCustomerProfileException;
import com.ait.app.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException customerException) {

		return new ResponseEntity<>(customerException.getErrorMessage(), customerException.getHttpStatus());
	}
    @ExceptionHandler(CustomerAddressException.class)
	ResponseEntity<String> handleCustomerAddressException(CustomerAddressException customerAddressException) {

		return new ResponseEntity(customerAddressException.getErrorMessage(), customerAddressException.getHttpStatus());
	}
    @ExceptionHandler(CustomerProfileException.class)
    ResponseEntity<String>handleCustomerProfileException(CustomerProfileException customerProfileException){
    	
    	return new ResponseEntity(customerProfileException.getErrorMessage(),customerProfileException.getHttpStatus());
    }
    @ExceptionHandler(UpdateCustomerProfileException.class)
    ResponseEntity<String>handleUpdateCustomerProfileException(UpdateCustomerProfileException updateCustomerProfileException){
    	return new ResponseEntity(updateCustomerProfileException.getErrorMessage(),updateCustomerProfileException.getHttpStatus());
    }
    @ExceptionHandler(RestaurantException.class)
    ResponseEntity<String>handleRestaurantException(RestaurantException restaurantException){
    	return new ResponseEntity<String>(restaurantException.getErrorMessage(),restaurantException.getHttpStatus());
    }
    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String>handleUserNotFoundException(UserNotFoundException userNotFoundException){
    	return new ResponseEntity<String>(userNotFoundException.getErrorMessage(),userNotFoundException.getHttpStatus());
    }
    @ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<String> handleDuplicateRoleException(DuplicateRoleException duplicateRoleException) {

		return new ResponseEntity<>(duplicateRoleException.getErroeMessage(), duplicateRoleException.getHttpStatus());

	}
    @ExceptionHandler(RoleValidationException.class)
	public ResponseEntity<String> RoleValidationException(RoleValidationException roleValidationException) {
		return new ResponseEntity<>(roleValidationException.getMessage(), roleValidationException.getStatus());

	}
    
	
	
	
}
