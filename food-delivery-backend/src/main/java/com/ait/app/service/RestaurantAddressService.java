package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.RestaurantAddressRequestDto;
import com.ait.app.dto.RestaurantAddressResponseDto;
import com.ait.app.model.RestaurantAddress;

public interface RestaurantAddressService {

	RestaurantAddressResponseDto addRestaurantAddress(int restaurantId, RestaurantAddressRequestDto requestDto);

	RestaurantAddressResponseDto getRestaurantAddress(int restaurantId);

	RestaurantAddressResponseDto updateRestaurantAddress(int restaurantId,
			RestaurantAddressRequestDto restaurantAddressRequestDto);

	public void deleteRestaurantAddress(int restaurantId);

	List<RestaurantAddressResponseDto> getRestaurantAddresses(int restaurantId);

}