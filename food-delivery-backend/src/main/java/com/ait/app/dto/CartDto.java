package com.ait.app.dto;

public class CartDto {
private int userId;
private int foodItemId;
private int quantity;
private int price;
private String foodName;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
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
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getFoodName() {
	return foodName;
}
public void setFoodName(String foodName) {
	this.foodName = foodName;
}

}
