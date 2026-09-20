package com.ait.app.dto;

public class RestaurantAddressRequestDto {

	private String restaurantNo;
	private String street;
	private String city;
	private String state;
	private String pincode;

	public String getRestaurantNo() {
		return restaurantNo;
	}

	public void setRestaurantNo(String restaurantNo) {
		this.restaurantNo = restaurantNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}