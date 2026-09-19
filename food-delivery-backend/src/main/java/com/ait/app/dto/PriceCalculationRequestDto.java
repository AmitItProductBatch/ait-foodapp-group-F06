package com.ait.app.dto;

import org.antlr.v4.runtime.misc.NotNull;
public class PriceCalculationRequestDto {
	
private int foodItemId;

private int quantity;
public int getFoodItemId() {
	return foodItemId;
}
public void setFoodItemId(int foodItemId) {
	this.foodItemId = foodItemId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}
