package com.ait.app.service;

import com.ait.app.dto.RestaurantAddressRequestDto;
import com.ait.app.dto.RestaurantAddressResponseDto;

public interface RestaurantAddressService {

	RestaurantAddressResponseDto addRestaurantAddress(int restaurantId, RestaurantAddressRequestDto requestDto);

	RestaurantAddressResponseDto getRestaurantAddress(int restaurantId);

	RestaurantAddressResponseDto updateRestaurantAddress(int restaurantId,
			RestaurantAddressRequestDto restaurantAddressRequestDto);

}