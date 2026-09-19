package com.ait.app.service;

import com.ait.app.dto.FoodCategoryRequestDto;
import com.ait.app.dto.FoodCategoryResponseDto;

public interface FoodCategoryService {
	
	FoodCategoryResponseDto addCategory(FoodCategoryRequestDto requestDto);
	
	FoodCategoryResponseDto getCategory(int categoryId );
}
