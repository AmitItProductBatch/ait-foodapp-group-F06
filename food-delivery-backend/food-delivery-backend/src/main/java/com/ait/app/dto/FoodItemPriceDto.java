package com.ait.app.dto;

public class FoodItemPriceDto {

	private int foodItemId;
	private String foodName;
	private Long price;
	
	public int getFoodItemId() {
		return foodItemId;
	}
	
	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}

	
}
