package com.ait.app.dto;

import java.math.BigDecimal;

public class DeliveryFeeRequestDto {

	private int restaurantAddressId;
	private int addressId;
	private BigDecimal orderAmount;

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getRestaurantAddressId() {
		return restaurantAddressId;
	}

	public void setRestaurantAddressId(int restaurantAddressId) {
		this.restaurantAddressId = restaurantAddressId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

}
