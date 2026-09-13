package com.ait.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.FoodItemDto;
import com.ait.app.exception.FoodItemException;
import com.ait.app.exception.UpdateCustomerProfileException;
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
	public FoodItemDto addFoodItem(FoodItemDto dto) {

		Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId()).get();
		if (restaurant == null) {
			throw new FoodItemException("Restaurant not found", HttpStatus.NOT_FOUND);
		}

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
		response.setRestaurantId(savedFoodItem.getRestaurant().getId());

		return response;
	}

	@Override
	public List<FoodItem> getAllFoodItems(int restaurantId) {
		List<FoodItem> list = foodItemRepository.findAllFoodItemByRestaurantId(restaurantId);
		return list;
	}

	@Override
	public FoodItemDto updateFoodItem(int id, FoodItemDto foodItemDto) {

		FoodItem existingFoodItem = foodItemRepository.findById(id)
				.orElseThrow(() -> new FoodItemException("FoodItem not found with id: " + id, HttpStatus.NOT_FOUND));

		existingFoodItem.setName(foodItemDto.getName());
		existingFoodItem.setDescription(foodItemDto.getDescription());
		existingFoodItem.setPrice(foodItemDto.getPrice());
		existingFoodItem.setAvailability(foodItemDto.getAvailability());
		existingFoodItem.setCategory(foodItemDto.getCategory());

		FoodItem foodItem = foodItemRepository.save(existingFoodItem);

		FoodItemDto foodItemResponse = new FoodItemDto();

		foodItemResponse.setName(foodItem.getName());
		foodItemResponse.setDescription(foodItem.getDescription());
		foodItemResponse.setPrice(foodItem.getPrice());
		foodItemResponse.setAvailability(foodItem.getAvailability());
		foodItemResponse.setCategory(foodItem.getCategory());

		return foodItemResponse;
	}
}