package com.ait.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.app.dto.FoodItemDto;
import com.ait.app.model.FoodItem;
import com.ait.app.model.Restaurant;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.repository.RestaurantRepository;
import com.ait.app.service.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private FoodItemRepository foodItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public FoodItemDto addFoodItem(int restaurantId, FoodItemDto dto) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new RuntimeException("Restaurant not found"));

		FoodItem foodItem = new FoodItem();

		foodItem.setName(dto.getName());
		foodItem.setDescription(dto.getDescription());
		foodItem.setPrice(dto.getPrice());
		foodItem.setAvailability(dto.getAvailability());
		foodItem.setCategory(dto.getCategory());

		foodItem.setRestaurant(restaurant);

		FoodItem savedFoodItem = foodItemRepository.save(foodItem);

		FoodItemDto response = new FoodItemDto();

		
		response.setName(savedFoodItem.getName());
		response.setDescription(savedFoodItem.getDescription());
		response.setPrice(savedFoodItem.getPrice());
		response.setAvailability(savedFoodItem.getAvailability());
		response.setCategory(savedFoodItem.getCategory());
		response.setRestaurantId(restaurant.getId());

		return response;
	}
}