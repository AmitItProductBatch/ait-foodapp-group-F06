package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.CartItemDto;
import com.ait.app.service.CartItemService;

@RestController
public class CartItemController {

	    @Autowired
	    CartItemService cartItemService;

	    @PostMapping("addCartItem")
	    public ResponseEntity<CartItemDto> createCartItem(
	            @RequestBody CartItemDto cartItemDto) {

	        CartItemDto dto =
	                cartItemService.saveCartItem(cartItemDto);

	        return new ResponseEntity<>(dto, HttpStatus.CREATED);
	    }
	}

