package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.ait.app.dto.CartDto;
import com.ait.app.dto.CartResponseDto;

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

	@GetMapping("getCart/{userId}")
	
    public ResponseEntity<CartResponseDto> getCart(@PathVariable int userId) {

        CartResponseDto cartResponseResponse = cartService.getCart(userId);

        return new ResponseEntity<>( cartResponseResponse , HttpStatus.OK);
    }
	
	@DeleteMapping("cart/{userId}")
	public ResponseEntity<Void> deleteCart(@PathVariable int userId){
		cartService.deleteCart(userId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
