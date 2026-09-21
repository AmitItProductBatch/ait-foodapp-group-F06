package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CartItemResponseDto;
import com.ait.app.dto.OrderTotalRequestDto;
import com.ait.app.dto.OrderTotalResponseDto;
import com.ait.app.exception.CartItemServiceException;
import com.ait.app.model.CartItem;
import com.ait.app.repository.CartItemRepository;
import com.ait.app.service.OrderTotalService;

@Service
public class OrderTotalServiceImpl implements OrderTotalService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public OrderTotalResponseDto calculateOrderTotal(OrderTotalRequestDto request) {

		List<CartItem> cartItems = cartItemRepository.findByCartId(request.getCartId());

		
		if (cartItems.isEmpty()) {
			throw new CartItemServiceException("Cart is empty", HttpStatus.BAD_REQUEST);
		}

		List<CartItemResponseDto> itemList = new ArrayList<>();

		int subtotal = 0;

		for (CartItem item : cartItems) {

			int itemTotal = item.getPrice() * item.getQuantity();

			CartItemResponseDto itemDto = new CartItemResponseDto();

			itemDto.setCartItemId(item.getId());
			itemDto.setCartId(item.getCart().getId());
			itemDto.setFoodItemId(item.getFooditem().getId());
			itemDto.setFoodName(item.getFooditem().getName());
			itemDto.setPrice(item.getPrice());
			itemDto.setQuantity(item.getQuantity());
			itemDto.setTotal(itemTotal);

			itemList.add(itemDto);

			subtotal = subtotal + itemTotal;
		}

		OrderTotalResponseDto response = new OrderTotalResponseDto();

		response.setItems(itemList);
		response.setSubtotal(subtotal);

		return response;
	}
}