package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.model.Restaurant;

public interface RestaurantService {
	public RestaurantDto addRestaurant(RestaurantDto restaurantDto);

	public List<Restaurant> GetAllRestaurant();

}
