package com.ait.app.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderHistoryResponseDto {
private int orderId;
private int restaurantId;

private String status;
private double totalAmount;
private String paymentMethod;
private LocalDateTime orderTime;
private int userId;
private List<Integer> orderItemId;
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getRestaurantId() {
	return restaurantId;
}
public void setRestaurantId(int restaurantId) {
	this.restaurantId = restaurantId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(double totalAmount) {
	this.totalAmount = totalAmount;
}
public String getPaymentMethod() {
	return paymentMethod;
}
public void setPaymentMethod(String paymentMethod) {
	this.paymentMethod = paymentMethod;
}
public LocalDateTime getOrderTime() {
	return orderTime;
}
public void setOrderTime(LocalDateTime orderTime) {
	this.orderTime = orderTime;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public List<Integer> getOrderItemId() {
	return orderItemId;
}
public void setOrderItemId(List<Integer> orderItemId) {
	this.orderItemId = orderItemId;
}

}
