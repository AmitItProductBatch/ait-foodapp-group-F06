package com.ait.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private long mobileNo;
	private double rating;
	private String cuisine;

	@OneToMany(mappedBy = "restaurant")
	private List<FoodItem> menuItems;

	@ManyToOne
	@JoinColumn(name = "userId")

	private User user;
	@OneToMany(mappedBy = "restaurant")
	private List<Cart> cart;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<FoodCategory> foodCategories;
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private RestaurantAddress restaurantAddress;

	public List<FoodCategory> getFoodItemCategories() {
		return foodCategories;
	}

	public void setFoodItemCategories(List<FoodCategory> foodCategories) {
		this.foodCategories = foodCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public List<FoodItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<FoodItem> menuItems) {
		this.menuItems = menuItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

}
