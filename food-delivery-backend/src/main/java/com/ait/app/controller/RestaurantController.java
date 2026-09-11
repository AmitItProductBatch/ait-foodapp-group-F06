package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.service.RestaurantService;

@RestController
public class RestaurantController {
	@Autowired
	RestaurantService restaurantService;

	@PostMapping("addRestaurant")
	ResponseEntity addRestaurant(@RequestBody RestaurantDto restaurantDto) {
		RestaurantDto dto = restaurantService.addRestaurant(restaurantDto);
		return new ResponseEntity(dto, HttpStatus.CREATED);
	}
}
