package com.ait.app.service;

import com.ait.app.dto.CartItemDto;
import com.ait.app.dto.CartResponseDto;

public interface CartItemService {

	CartItemDto saveCartItem(CartItemDto cartitemdto);

	CartResponseDto updateCartItemQuantity(int userId, int cartItemId, int quantity);
}
