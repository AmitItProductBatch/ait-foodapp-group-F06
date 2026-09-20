package com.ait.app.service;

import com.ait.app.dto.FoodCategoryRequestDto;
import com.ait.app.dto.FoodCategoryResponseDto;
import com.ait.app.dto.FoodItemDto;

public interface FoodCategoryService {
	
	FoodCategoryResponseDto addCategory(FoodCategoryRequestDto requestDto);
	
	FoodCategoryResponseDto getCategory(int categoryId );
	
	FoodCategoryResponseDto updateCategory(int categoryId, FoodCategoryRequestDto requestDto);
	
}
