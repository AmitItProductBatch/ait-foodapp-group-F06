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
import org.springframework.web.service.annotation.GetExchange;

import com.ait.app.dto.FoodItemDto;
import com.ait.app.model.FoodItem;
import com.ait.app.service.FoodItemService;

@RestController
@RequestMapping("/api")
public class FoodItemController {

	@Autowired
	private FoodItemService foodItemService;

	@PostMapping("/restaurants/addFoodItem")
	public ResponseEntity<FoodItemDto> addFoodItem( @RequestBody FoodItemDto dto) {

		FoodItemDto foodItemDto= foodItemService.addFoodItem(dto);
		return new ResponseEntity(foodItemDto,HttpStatus.CREATED);
	}

	@GetMapping("getFoodItems/{restaurantId}")
	ResponseEntity<List<FoodItem>> getAllFoodItems(@PathVariable int restaurantId) {

		List<FoodItem> list = foodItemService.getAllFoodItems(restaurantId);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("udateFoodItem/{id}")
	ResponseEntity<FoodItem> updateFoodItem(@PathVariable int id, @RequestBody FoodItemDto foodItemDto){
		
		FoodItemDto updatedFoodItem = foodItemService.updateFoodItem(id, foodItemDto);
		return new ResponseEntity(updatedFoodItem, HttpStatus.OK);
	}
	
	
}
