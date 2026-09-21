package com.ait.app.dto;

public class PriceCalculationResponseDto {
private int foodItemId;
private int quantity;
private int unitPrice;
private int total;
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
public int getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(int unitPrice) {
	this.unitPrice = unitPrice;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}

}
