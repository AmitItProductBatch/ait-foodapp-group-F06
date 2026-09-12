package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.FoodItemDto;
import com.ait.app.service.FoodItemService;

@RestController
@RequestMapping("/api")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/restaurants/{restaurantId}/addFoodItem")
    public ResponseEntity<FoodItemDto> addFoodItem(@PathVariable int restaurantId, @RequestBody FoodItemDto dto) {

        FoodItemDto response =foodItemService.addFoodItem(restaurantId, dto);

        return new ResponseEntity<FoodItemDto>(response, HttpStatus.CREATED);
    }
}
