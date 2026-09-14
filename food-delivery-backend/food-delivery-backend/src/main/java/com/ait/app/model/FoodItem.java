package com.ait.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String description;
	private Long price;
	private Boolean availability;
	private String category;

	
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}


	/**
	 * @return the availability
	 */
	public Boolean getAvailability() {
		return availability;
	}


	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}


	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}


	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



}
