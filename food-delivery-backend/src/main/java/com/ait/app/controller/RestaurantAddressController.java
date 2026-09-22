package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ait.app.dto.RestaurantAddressRequestDto;
import com.ait.app.dto.RestaurantAddressResponseDto;
import com.ait.app.service.RestaurantAddressService;

@RestController
@RequestMapping("/api")
public class RestaurantAddressController {

	@Autowired
	RestaurantAddressService restaurantAddressService;

	@PostMapping("/addRestaurantAddress/{restaurantId}")
	public ResponseEntity<RestaurantAddressResponseDto> addRestaurantAddress(@PathVariable int restaurantId,
			@RequestBody RestaurantAddressRequestDto requestDto) {

		RestaurantAddressResponseDto response = restaurantAddressService.addRestaurantAddress(restaurantId, requestDto);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/getRestaurantAddress/{restaurantId}")
	public ResponseEntity<RestaurantAddressResponseDto> getRestaurantAddress(@PathVariable int restaurantId) {

		RestaurantAddressResponseDto response = restaurantAddressService.getRestaurantAddress(restaurantId);

		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PutMapping("updateRestaurantAddress/{restaurantId}")
	ResponseEntity updateRestaurantAddress(@PathVariable int restaurantId,
			@RequestBody RestaurantAddressRequestDto restaurantAddressRequestDto) {

		RestaurantAddressResponseDto restaurantAddressResponseDto = restaurantAddressService
				.updateRestaurantAddress(restaurantId, restaurantAddressRequestDto);
		return new ResponseEntity(restaurantAddressResponseDto, HttpStatus.OK);
	}

	@DeleteMapping("deleteRestaurantAddress/{restaurantId}")
	ResponseEntity deleteRestaurantAddress(@PathVariable int restaurantId) {

		restaurantAddressService.deleteRestaurantAddress(restaurantId);
		return new ResponseEntity("restaurant deleted", HttpStatus.NO_CONTENT);

	}
	@GetMapping("getAllAddressOfRestaurant/{restaurantId}")
	ResponseEntity getAddressOfRestaurant(@PathVariable int restaurantId) {
		
	List<RestaurantAddressResponseDto>list=	restaurantAddressService.getRestaurantAddresses(restaurantId);
	
	return new ResponseEntity(list,HttpStatus.OK);
	
	}
}