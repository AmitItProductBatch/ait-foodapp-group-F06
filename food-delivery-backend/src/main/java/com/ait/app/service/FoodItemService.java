package com.ait.app.service;

import com.ait.app.dto.FoodItemDto;

public interface FoodItemService {
	
	

	FoodItemDto addFoodItem(int restaurantId, FoodItemDto dto);

}
