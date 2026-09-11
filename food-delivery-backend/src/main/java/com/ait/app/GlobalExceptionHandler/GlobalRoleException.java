package com.ait.app.GlobalExceptionHandler;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ait.app.exception.DuplicateRoleException;
import com.ait.app.exception.RoleValidationException;

@ControllerAdvice
public class GlobalRoleException {

	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<String> handleDuplicateRoleException(DuplicateRoleException duplicateRoleException) {

		return new ResponseEntity<>(duplicateRoleException.getErroeMessage(), duplicateRoleException.getHttpStatus());

	}

	@ExceptionHandler(RoleValidationException.class)
	public ResponseEntity<String> RoleValidationException(RoleValidationException roleValidationException) {
		return new ResponseEntity<>(roleValidationException.getMessage(), roleValidationException.getStatus());

	}

}
