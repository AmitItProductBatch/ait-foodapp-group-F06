package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.RestaurantAddressRequestDto;
import com.ait.app.dto.RestaurantAddressResponseDto;
import com.ait.app.exception.RestaurantException;
import com.ait.app.model.Restaurant;
import com.ait.app.model.RestaurantAddress;
import com.ait.app.repository.RestaurantAddressRepository;
import com.ait.app.repository.RestaurantRepository;
import com.ait.app.service.RestaurantAddressService;

@Service
public class RestaurantAddressServiceImpl implements RestaurantAddressService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;

	@Override
	public RestaurantAddressResponseDto addRestaurantAddress(int restaurantId, RestaurantAddressRequestDto requestDto) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
				() -> new RestaurantException("Restaurant not found with id : " + restaurantId, HttpStatus.NOT_FOUND));

		RestaurantAddress address = new RestaurantAddress();

		address.setRestaurantNo(requestDto.getRestaurantNo());
		address.setStreet(requestDto.getStreet());
		address.setCity(requestDto.getCity());
		address.setState(requestDto.getState());
		address.setPincode(requestDto.getPincode());
		address.setRestaurant(restaurant);

		RestaurantAddress savedAddress = restaurantAddressRepository.save(address);

		return convertToResponse(savedAddress);
	}

	@Override
	public RestaurantAddressResponseDto getRestaurantAddress(int restaurantId) {

		RestaurantAddress address = restaurantAddressRepository.findByRestaurantId(restaurantId)
				.orElseThrow(() -> new RestaurantException("Address not found for restaurant id : " + restaurantId,
						HttpStatus.NOT_FOUND));

		return convertToResponse(address);
	}

	private RestaurantAddressResponseDto convertToResponse(RestaurantAddress address) {

		RestaurantAddressResponseDto response = new RestaurantAddressResponseDto();

		response.setId(address.getId());
		response.setRestaurantId(address.getRestaurant().getId());
		response.setRestaurantNo(address.getRestaurantNo());
		response.setStreet(address.getStreet());
		response.setCity(address.getCity());
		response.setState(address.getState());
		response.setPincode(address.getPincode());

		return response;
	}
}