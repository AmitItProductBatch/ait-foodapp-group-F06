package com.ait.app.dto;

public class OrderItemResponseDto {

	    private int orderItemId;
	    private String foodName;
	    private int quantity;
	    private double unitPrice;
	    private double totalPrice;

	    public int getOrderItemId() {
	        return orderItemId;
	    }

	    public void setOrderItemId(int orderItemId) {
	        this.orderItemId = orderItemId;
	    }

	    public String getFoodName() {
	        return foodName;
	    }

	    public void setFoodName(String foodName) {
	        this.foodName = foodName;
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

