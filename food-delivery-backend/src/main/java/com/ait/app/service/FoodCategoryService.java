package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.FoodCategoryRequestDto;
import com.ait.app.dto.FoodCategoryResponseDto;
import com.ait.app.dto.FoodItemDto;
import com.ait.app.model.FoodCategory;

public interface FoodCategoryService {

	FoodCategoryResponseDto addCategory(FoodCategoryRequestDto requestDto);

	FoodCategoryResponseDto getCategory(int categoryId);

	FoodCategoryResponseDto updateCategory(int categoryId, FoodCategoryRequestDto requestDto);

	List<FoodCategoryResponseDto> getFoodCategories(int restaurantId);
}
