package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.CartDto;
import com.ait.app.service.CartService;

@RestController
@RequestMapping("api")
public class CartController {
	@Autowired
	CartService cartService;

	@PostMapping("addCart")
	ResponseEntity createCart(@RequestBody CartDto cartDto) {
		CartDto dto = cartService.createCart(cartDto);
		return new ResponseEntity(dto, HttpStatus.CREATED);
	}
}
