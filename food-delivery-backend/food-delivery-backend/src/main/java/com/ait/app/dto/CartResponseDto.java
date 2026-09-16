package com.ait.app.dto;

import java.util.List;

public class CartResponseDto {
	private int cartId;
	private int restaurantId;
	private List<CartItemResponseDto> items;
	private int totalAmount;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public List<CartItemResponseDto> getItems() {
		return items;
	}
	public void setItems(List<CartItemResponseDto> items) {
		this.items = items;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	

}
