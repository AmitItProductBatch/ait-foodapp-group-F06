package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CartItemDto;
import com.ait.app.dto.CartItemResponseDto;
import com.ait.app.dto.CartResponseDto;
import com.ait.app.exception.CartItemNotFoundException;
import com.ait.app.exception.CartItemServiceException;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.exception.InvalidQuantityException;
import com.ait.app.model.Cart;
import com.ait.app.model.CartItem;
import com.ait.app.model.FoodItem;
import com.ait.app.repository.CartItemRepository;
import com.ait.app.repository.CartRepository;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.service.CartItemService;

@Service
public class CartItemServiceimpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public CartItemDto saveCartItem(CartItemDto cartItemDto) {

		Optional<Cart> cartOptional = cartRepository.findById(cartItemDto.getCartId());

		if (cartOptional.isEmpty()) {
			throw new CartItemServiceException("Cart not found", HttpStatus.NOT_FOUND);
		}

		Cart cart = cartOptional.get();

		Optional<FoodItem> foodItemOptional = foodItemRepository.findById(cartItemDto.getFoodItemId());

		if (foodItemOptional.isEmpty()) {
			throw new FoodItemNotFoundException("Food item not found", HttpStatus.NOT_FOUND);
		}

		FoodItem foodItem = foodItemOptional.get();

		if (cartItemDto.getQuantity() <= 0) {
			throw new CartItemServiceException("Quantity must be greater than 0", HttpStatus.BAD_REQUEST);
		}

		CartItem cartItem = new CartItem();

		cartItem.setCart(cart);
		cartItem.setFooditem(foodItem);
		cartItem.setQuantity(cartItemDto.getQuantity());

		cartItem.setPrice(foodItem.getPrice());

		int total = foodItem.getPrice() * cartItemDto.getQuantity();

		cartItem.setTotal(total);

		CartItem savedCartItem = cartItemRepository.save(cartItem);

		CartItemDto dto = new CartItemDto();

		dto.setCartId(savedCartItem.getCart().getId());
		dto.setFoodItemId(savedCartItem.getFooditem().getId());
		dto.setQuantity(savedCartItem.getQuantity());
		dto.setPrice(savedCartItem.getPrice());
		dto.setTotal(savedCartItem.getTotal());

		return dto;
	}

	@Override
	public CartResponseDto updateCartItemQuantity(int userId, int cartItemId, int quantity) {
		if (quantity < 0) {
			throw new InvalidQuantityException("item quantity cannot be negative", HttpStatus.BAD_REQUEST);
		}
		Optional<CartItem> o = cartItemRepository.findByIdAndCart_UserId(cartItemId, userId);
		if (o.isEmpty()) {
			throw new CartItemNotFoundException("cart item is empty", HttpStatus.NOT_FOUND);
		}
		CartItem cartItem = o.get();
		if (quantity == 0) {
			cartItemRepository.delete(cartItem);
		} else {
			cartItem.setQuantity(quantity);

		}
		int subtotal = quantity * cartItem.getPrice();

		cartItem.setTotal(subtotal);
		cartItemRepository.save(cartItem);

		List<Cart> cartList = cartRepository.findByUserId(userId);
		CartResponseDto cartResponseDto = new CartResponseDto();
		List<CartItemResponseDto> cartItemList = new ArrayList();
		int cartTotal = 0;

		for (Cart cart : cartList) {
			cartResponseDto.setCartId(cart.getId());
			if (cart.getRestaurant() != null) {
				cartResponseDto.setRestaurantId(cart.getRestaurant().getId());
			}
			for (CartItem item : cart.getCartItem()) {
				CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();

				cartItemResponseDto.setCartId(cart.getId());
				cartItemResponseDto.setFoodItemId(item.getFooditem().getId());
				cartItemResponseDto.setQuantity(item.getQuantity());
				cartItemResponseDto.setPrice(item.getPrice());
				cartItemResponseDto.setTotal(item.getTotal());
				cartItemResponseDto.setFoodName(item.getFooditem().getName());
				cartItemResponseDto.setCartItemId(item.getId());
				cartItemList.add(cartItemResponseDto);
				cartTotal += item.getTotal();

			}

		}

		cartResponseDto.setItems(cartItemList);
		cartResponseDto.setTotalAmount(cartTotal);

		return cartResponseDto;
	}

	@Override
	public void deleteCartItem(int id) {
		if (!cartItemRepository.existsById(id)) {
			throw new CartItemServiceException("Cart item not found with id:" + id, HttpStatus.NOT_FOUND);
		}

		cartItemRepository.deleteById(id);

	}
}