package com.ait.app.dto;

import java.util.List;

public class OrderTotalResponseDto {

	private List<CartItemResponseDto> items;

	private int subtotal;

	public List<CartItemResponseDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponseDto> items) {
		this.items = items;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
}