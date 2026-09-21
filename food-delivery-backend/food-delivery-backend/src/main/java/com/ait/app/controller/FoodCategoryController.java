package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.FoodCategoryRequestDto;
import com.ait.app.dto.FoodCategoryResponseDto;
import com.ait.app.model.FoodCategory;
import com.ait.app.service.FoodCategoryService;

@RestController
@RequestMapping("/api/foodCategories")
public class FoodCategoryController {

	@Autowired
	private FoodCategoryService foodCategoryService;

	@PostMapping
	public ResponseEntity<FoodCategoryResponseDto> addCategory(@RequestBody FoodCategoryRequestDto requestDto) {

		FoodCategoryResponseDto responseDto = foodCategoryService.addCategory(requestDto);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping("/{categoryId}")

	public ResponseEntity<FoodCategoryResponseDto> getCategoryById(@PathVariable int categoryId) {

		FoodCategoryResponseDto responseDto = foodCategoryService.getCategory(categoryId);

		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<FoodCategoryResponseDto> updateCategory(@PathVariable int categoryId,
			@RequestBody FoodCategoryRequestDto requestDto) {

		FoodCategoryResponseDto responseDto = foodCategoryService.updateCategory(categoryId, requestDto);

		return new ResponseEntity(responseDto, HttpStatus.OK);
	}

	@GetMapping("getAll/{restaurantId}")
	ResponseEntity getAllCategories(@PathVariable int restaurantId) {
		List<FoodCategoryResponseDto> list = foodCategoryService.getFoodCategories(restaurantId);

		return new ResponseEntity(list, HttpStatus.OK);

	}

}
