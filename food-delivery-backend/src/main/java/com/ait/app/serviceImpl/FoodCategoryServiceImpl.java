package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.FoodCategoryRequestDto;
import com.ait.app.dto.FoodCategoryResponseDto;
import com.ait.app.exception.FoodCategoryException;
import com.ait.app.exception.RestaurantException;
import com.ait.app.model.FoodCategory;
import com.ait.app.model.Restaurant;
import com.ait.app.repository.FoodCategoryRepository;
import com.ait.app.repository.RestaurantRepository;
import com.ait.app.service.FoodCategoryService;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

	@Autowired
	private FoodCategoryRepository foodCategoryRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public FoodCategoryResponseDto addCategory(FoodCategoryRequestDto requestDto) {

		Optional<Restaurant> o = restaurantRepository.findById(requestDto.getRestaurantId());
		
		if(o.isEmpty()) {
			throw new RestaurantException("Restaurant Not Found",
					HttpStatus.NOT_FOUND);
			
		}
		Restaurant restaurant = o.get();
				

		if (foodCategoryRepository.existsByNameAndRestaurantId(requestDto.getName(),
				requestDto.getRestaurantId())) {

			throw new FoodCategoryException("Category already exists",
					HttpStatus.BAD_REQUEST);
		}

		FoodCategory category = new FoodCategory();
		category.setName(requestDto.getName());

		category.setRestaurant(restaurant);

		FoodCategory saved = foodCategoryRepository.save(category);

		FoodCategoryResponseDto response = new FoodCategoryResponseDto();
		response.setId(saved.getId());
		response.setName(saved.getName());

		response.setRestaurantId(restaurant.getId());

		return response;
	}

	@Override
	public FoodCategoryResponseDto getCategory(int categoryId) {
		
		
		Optional<FoodCategory> optionalCategory =
				foodCategoryRepository.findById(categoryId);
		
		
		if(optionalCategory.isEmpty()) {
			throw new FoodCategoryException (
				"Food Category not found",
				HttpStatus.NOT_FOUND
				
			);
		}
				
		FoodCategory foodCategory = optionalCategory.get();
		
		FoodCategoryResponseDto categoryResponseDto = new FoodCategoryResponseDto();
		
		categoryResponseDto.setId(foodCategory.getId());
		categoryResponseDto.setName(foodCategory.getName());
		categoryResponseDto.setRestaurantId(foodCategory.getRestaurant().getId());
		
		return categoryResponseDto;
	}

}
