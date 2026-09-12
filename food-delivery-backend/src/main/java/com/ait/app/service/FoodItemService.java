package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.FoodItemDto;
import com.ait.app.model.FoodItem;

public interface FoodItemService {
	
	FoodItemDto addFoodItem( FoodItemDto dto);
    List<FoodItem>getAllFoodItems(int restaurantId);
    
    FoodItemDto updateFoodItem(int id, FoodItemDto foodItemDto);
    
}
