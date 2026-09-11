package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.model.Restaurant;
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

	@GetMapping("viewRestaurant")
	ResponseEntity<List<Restaurant>> GetAllRestaurant() {
		List<Restaurant> restaurant = restaurantService.GetAllRestaurant();
		return new ResponseEntity<List<Restaurant>>(restaurant, HttpStatus.OK);
	}

	@GetMapping("getRestaurant/{id}")
	ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) {

		Restaurant restaurant = restaurantService.getRestaurant(id);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

}
