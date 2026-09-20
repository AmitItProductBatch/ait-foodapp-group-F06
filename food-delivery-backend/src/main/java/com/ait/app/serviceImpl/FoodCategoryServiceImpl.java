package com.ait.app.serviceImpl;

import java.util.ArrayList;

import java.util.List;
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
import com.ait.app.dto.RestaurantDto;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

	@Autowired
	FoodCategoryRepository foodCategoryRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public FoodCategoryResponseDto addCategory(FoodCategoryRequestDto requestDto) {

		Optional<Restaurant> o = restaurantRepository.findById(requestDto.getRestaurantId());

		if (o.isEmpty()) {
			throw new RestaurantException("Restaurant Not Found", HttpStatus.NOT_FOUND);

		}
		Restaurant restaurant = o.get();

		if (foodCategoryRepository.existsByNameAndRestaurantId(requestDto.getName(), requestDto.getRestaurantId())) {

			throw new FoodCategoryException("Category already exists", HttpStatus.BAD_REQUEST);
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

		Optional<FoodCategory> optionalCategory = foodCategoryRepository.findById(categoryId);

		if (optionalCategory.isEmpty()) {
			throw new FoodCategoryException("Food Category not found", HttpStatus.NOT_FOUND

			);
		}

		FoodCategory foodCategory = optionalCategory.get();

		FoodCategoryResponseDto categoryResponseDto = new FoodCategoryResponseDto();

		categoryResponseDto.setId(foodCategory.getId());
		categoryResponseDto.setName(foodCategory.getName());
		categoryResponseDto.setRestaurantId(foodCategory.getRestaurant().getId());

		return categoryResponseDto;
	}

	@Override
	public FoodCategoryResponseDto updateCategory(int categoryId, FoodCategoryRequestDto requestDto) {

		Optional<FoodCategory> o = foodCategoryRepository.findById(categoryId);
		if (o.isEmpty()) {

			throw new FoodCategoryException("food category not found", HttpStatus.NOT_FOUND);
		}

		FoodCategory foodCategory = o.get();

		Optional<Restaurant> optional = restaurantRepository.findById(requestDto.getRestaurantId());
		if (optional.isEmpty()) {
			throw new RestaurantException("restaurant not found ", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant = optional.get();
		foodCategory.setName(requestDto.getName());
		foodCategory.setRestaurant(restaurant);

		FoodCategory category = foodCategoryRepository.save(foodCategory);

		FoodCategoryResponseDto foodCategoryResponseDto = new FoodCategoryResponseDto();
		foodCategoryResponseDto.setId(category.getId());
		foodCategoryResponseDto.setName(category.getName());
		foodCategoryResponseDto.setRestaurantId(category.getRestaurant().getId());
		return foodCategoryResponseDto;

	}

	@Override
	public List<FoodCategoryResponseDto> getFoodCategories(int restaurantId) {
		Optional<Restaurant> o = restaurantRepository.findById(restaurantId);
		if (o.isEmpty()) {
			throw new RestaurantException("restaurant not found ", HttpStatus.NOT_FOUND);
		}

		List<FoodCategory> l = foodCategoryRepository.findAllCategoriesByRestaurantId(restaurantId);

		List<FoodCategoryResponseDto> responseList = new ArrayList();
		for (FoodCategory foodCategory : l) {
			FoodCategoryResponseDto foodCategoryResponseDto = new FoodCategoryResponseDto();
			foodCategoryResponseDto.setId(foodCategory.getId());
			foodCategoryResponseDto.setName(foodCategory.getName());
			foodCategoryResponseDto.setRestaurantId(restaurantId);
			responseList.add(foodCategoryResponseDto);
		}
		return responseList;
	}

	@Override
	public List<RestaurantDto> getRestaurantByCategory(String categoryName) {

		List<FoodCategory> categories = foodCategoryRepository.findByName(categoryName);

		if (categories.isEmpty()) {

			throw new FoodCategoryException("No restaurant found for category: " + categoryName, HttpStatus.NOT_FOUND);
		}

		List<RestaurantDto> restaurantList = new ArrayList<>();

		for (FoodCategory foodCategory : categories) {

			Restaurant restaurant = foodCategory.getRestaurant();

			RestaurantDto restaurantDto = new RestaurantDto();

			restaurantDto.setName(restaurant.getName());

			restaurantDto.setAddress(restaurant.getAddress());

			restaurantDto.setCuisine(restaurant.getCuisine());

			restaurantDto.setMobileNo(restaurant.getMobileNo());
			restaurantDto.setRating(restaurant.getRating());

			restaurantDto.setUserId(restaurant.getUser().getId());

			restaurantList.add(restaurantDto);
		}

		return restaurantList;
	}

}
