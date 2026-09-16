package com.ait.app.service;

import com.ait.app.dto.CartDto;
import com.ait.app.dto.CartResponseDto;

public interface CartService {
	CartDto createCart(CartDto cartDto);

	CartResponseDto getCart(int userId);
}
