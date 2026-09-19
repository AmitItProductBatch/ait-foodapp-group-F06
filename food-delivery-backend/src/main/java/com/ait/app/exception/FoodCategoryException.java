package com.ait.app.exception;

import org.springframework.http.HttpStatus;

public class FoodCategoryException extends RuntimeException{
	
	private HttpStatus status;

    public FoodCategoryException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
