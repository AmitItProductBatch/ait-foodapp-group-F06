package com.ait.app.dto;

public class OrderItemResponseDto {

	private int orderItemId;
	private String foodItemId;

	private int quantity;
	private double unitPrice;
	private double totalPrice;
	

	public String getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(String foodItemId) {
		this.foodItemId = foodItemId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
