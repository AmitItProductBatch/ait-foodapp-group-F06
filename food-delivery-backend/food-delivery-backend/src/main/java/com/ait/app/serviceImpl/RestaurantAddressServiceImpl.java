package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.RestaurantAddressRequestDto;
import com.ait.app.dto.RestaurantAddressResponseDto;
import com.ait.app.exception.RestaurantAddressNotFoundException;
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

		Optional<Restaurant> o = restaurantRepository.findById(requestDto.getRestaurantId());
		if (o.isEmpty()) {
			throw new RestaurantException("restaurant not found exception", HttpStatus.NOT_FOUND);

		}
		Restaurant restaurant = o.get();

		RestaurantAddress address = new RestaurantAddress();

		address.setContactNo(requestDto.getContactNo());
		address.setStreet(requestDto.getStreet());
		address.setCity(requestDto.getCity());
		address.setState(requestDto.getState());
		address.setPinCode(requestDto.getPincode());
		address.setRestaurant(restaurant);
		address.setBuildingName(requestDto.getBuildingName());

		RestaurantAddress savedAddress = restaurantAddressRepository.save(address);

		RestaurantAddressResponseDto restaurantAddressResponseDto = new RestaurantAddressResponseDto();
		restaurantAddressResponseDto.setCity(savedAddress.getCity());
		restaurantAddressResponseDto.setPincode(savedAddress.getPinCode());
		restaurantAddressResponseDto.setRestaurantId(savedAddress.getRestaurant().getId());
		restaurantAddressResponseDto.setContactNo(savedAddress.getContactNo());
		restaurantAddressResponseDto.setState(savedAddress.getState());
		restaurantAddressResponseDto.setStreet(savedAddress.getStreet());
		restaurantAddressResponseDto.setBuildingName(savedAddress.getBuildingName());

		return restaurantAddressResponseDto;

	}

	@Override
	public RestaurantAddressResponseDto getRestaurantAddress(int restaurantId) {
		Optional<RestaurantAddress> o = restaurantAddressRepository.findById(restaurantId);
		if (o.isEmpty()) {
			throw new RestaurantAddressNotFoundException("restaurant address not found", HttpStatus.NOT_FOUND);

		}
		RestaurantAddress restaurantAddress = o.get();

		RestaurantAddressResponseDto restaurantAddressResponseDto = new RestaurantAddressResponseDto();
		restaurantAddressResponseDto.setBuildingName(restaurantAddress.getBuildingName());
		restaurantAddressResponseDto.setCity(restaurantAddress.getCity());
		restaurantAddressResponseDto.setContactNo(restaurantAddress.getContactNo());
		restaurantAddressResponseDto.setPincode(restaurantAddress.getPinCode());
		restaurantAddressResponseDto.setRestaurantId(restaurantAddress.getRestaurant().getId());
		restaurantAddressResponseDto.setState(restaurantAddress.getState());
		restaurantAddressResponseDto.setStreet(restaurantAddress.getStreet());
		return restaurantAddressResponseDto;

	}

	@Override
	public RestaurantAddressResponseDto updateRestaurantAddress(int restaurantId,
			RestaurantAddressRequestDto restaurantAddressRequestDto) {
		Optional<RestaurantAddress> o = restaurantAddressRepository.findById(restaurantId);
		if (o.isEmpty()) {
			throw new RestaurantAddressNotFoundException("restaurant not found", HttpStatus.NOT_FOUND);
		}
		RestaurantAddress restaurantAddress = o.get();
		Optional<Restaurant> optional = restaurantRepository.findById(restaurantAddressRequestDto.getRestaurantId());
		if (o.isEmpty()) {
			throw new RestaurantException("restaurant not found", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant = optional.get();
		restaurantAddress.setBuildingName(restaurantAddressRequestDto.getBuildingName());
		restaurantAddress.setCity(restaurantAddressRequestDto.getCity());
		restaurantAddress.setContactNo(restaurantAddressRequestDto.getContactNo());
		restaurantAddress.setPinCode(restaurantAddressRequestDto.getPincode());
		restaurantAddress.setRestaurant(restaurant);
		restaurantAddress.setState(restaurantAddressRequestDto.getState());
		restaurantAddress.setStreet(restaurantAddressRequestDto.getStreet());
		RestaurantAddress address = restaurantAddressRepository.save(restaurantAddress);
         RestaurantAddressResponseDto restaurantAddressResponseDto=new RestaurantAddressResponseDto();
         
         restaurantAddressResponseDto.setBuildingName(address.getBuildingName());
         restaurantAddressResponseDto.setCity(address.getCity());
         restaurantAddressResponseDto.setContactNo(address.getContactNo());
         restaurantAddressResponseDto.setPincode(address.getPinCode());
         restaurantAddressResponseDto.setRestaurantId(address.getRestaurant().getId());
         restaurantAddressResponseDto.setState(address.getState());
         restaurantAddressResponseDto.setStreet(address.getStreet());
         
		return restaurantAddressResponseDto;
	}
}