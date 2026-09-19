package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.CartItemDto;
import com.ait.app.dto.CartResponseDto;
import com.ait.app.dto.QuantityDto;
import com.ait.app.service.CartItemService;

@RestController
public class CartItemController {

	@Autowired
	CartItemService cartItemService;

	@PostMapping("addCartItem")
	public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto) {

		CartItemDto dto = cartItemService.saveCartItem(cartItemDto);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@PutMapping("cartItems/{cartItemId}/{userId}")
	ResponseEntity<CartResponseDto> updateCartItemQuantity(@PathVariable int cartItemId, @PathVariable int userId,
			@RequestBody QuantityDto quantityDto) {

		CartResponseDto cartResponseDto = cartItemService.updateCartItemQuantity(userId, cartItemId,
				quantityDto.getQuantity());

		return new ResponseEntity(cartResponseDto, HttpStatus.OK);

	}

	@DeleteMapping("deleteCartItem/{id}")
	public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
		cartItemService.deleteCartItem(id);

		return new ResponseEntity<>("Cart item deleted successfully", HttpStatus.OK);
	}
	
}
