package com.ait.app.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ait.app.model.Cart;

public class CartNotFoundException extends RuntimeException {
	
	
	    private HttpStatus status;

	    public CartNotFoundException(String message, HttpStatus status) {
	        super(message);
	        this.status = status;
	    }

	    public HttpStatus getStatus() {
	        return status;
	    }
	
	

}
