package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
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
import com.ait.app.service.GeocodingService;
import com.ait.app.service.RestaurantAddressService;

@Service
public class RestaurantAddressServiceImpl implements RestaurantAddressService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;
    @Autowired
    GeocodingService geocodingService;
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
		String fullAddress =
		        requestDto.getStreet() + ", "
		        + requestDto.getCity() + ", "
		        + requestDto.getState() + ", "
		        + requestDto.getPincode() + ", India";

		double[] coordinates =
		        geocodingService.getCoordinates(fullAddress);

		address.setLatitude(coordinates[0]);
		address.setLongitude(coordinates[1]);

		RestaurantAddress savedAddress = restaurantAddressRepository.save(address);

		RestaurantAddressResponseDto restaurantAddressResponseDto = new RestaurantAddressResponseDto();
		restaurantAddressResponseDto.setCity(savedAddress.getCity());
		restaurantAddressResponseDto.setPincode(savedAddress.getPinCode());
		restaurantAddressResponseDto.setRestaurantId(savedAddress.getRestaurant().getId());
		restaurantAddressResponseDto.setContactNo(savedAddress.getContactNo());
		restaurantAddressResponseDto.setState(savedAddress.getState());
		restaurantAddressResponseDto.setStreet(savedAddress.getStreet());
		restaurantAddressResponseDto.setBuildingName(savedAddress.getBuildingName());
        restaurantAddressResponseDto.setLatitude(savedAddress.getLatitude());
        restaurantAddressResponseDto.setLongitude(savedAddress.getLongitude());
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
		restaurantAddressResponseDto.setLatitude(restaurantAddress.getLatitude());
		restaurantAddressResponseDto.setLongitude(restaurantAddress.getLongitude());
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
		if (optional.isEmpty()) {
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
		String fullAddress =
		        restaurantAddressRequestDto.getStreet() + ", "
		        + restaurantAddressRequestDto.getCity() + ", "
		        + restaurantAddressRequestDto.getState() + ", "
		        + restaurantAddressRequestDto.getPincode() + ", India";

		double[] coordinates =
		        geocodingService.getCoordinates(fullAddress);

		restaurantAddress.setLatitude(coordinates[0]);
		restaurantAddress.setLongitude(coordinates[1]);
		RestaurantAddress address = restaurantAddressRepository.save(restaurantAddress);
		RestaurantAddressResponseDto restaurantAddressResponseDto = new RestaurantAddressResponseDto();

		restaurantAddressResponseDto.setBuildingName(address.getBuildingName());
		restaurantAddressResponseDto.setCity(address.getCity());
		restaurantAddressResponseDto.setContactNo(address.getContactNo());
		restaurantAddressResponseDto.setPincode(address.getPinCode());
		restaurantAddressResponseDto.setRestaurantId(address.getRestaurant().getId());
		restaurantAddressResponseDto.setState(address.getState());
		restaurantAddressResponseDto.setStreet(address.getStreet());
		restaurantAddressResponseDto.setLatitude(address.getLatitude());
		restaurantAddressResponseDto.setLongitude(address.getLongitude());

		return restaurantAddressResponseDto;
	}

	@Override
	public void deleteRestaurantAddress(int restaurantId) {
		Optional<Restaurant> o = restaurantRepository.findById(restaurantId);
		if (o.isEmpty()) {
			throw new RestaurantException("restaurant not found", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant = o.get();

		List<RestaurantAddress> list = restaurantAddressRepository.findByRestaurantId(restaurantId);
		

		restaurantAddressRepository.deleteAll(list);

	}

	@Override
	public List<RestaurantAddressResponseDto> getRestaurantAddresses(int restaurantId) {
		Optional<Restaurant> o = restaurantRepository.findById(restaurantId);
		if (o.isEmpty()) {
			throw new RestaurantException("restaurant not found", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant = o.get();
		List<RestaurantAddress> addressList = restaurantAddressRepository.findByRestaurantId(restaurantId);
		
		
		List<RestaurantAddressResponseDto> responseList = new ArrayList();
		for (RestaurantAddress restaurantAddress : addressList) {

			RestaurantAddressResponseDto restaurantAddressResponseDto = new RestaurantAddressResponseDto();
			restaurantAddressResponseDto.setBuildingName(restaurantAddress.getBuildingName());
			restaurantAddressResponseDto.setCity(restaurantAddress.getCity());
			restaurantAddressResponseDto.setContactNo(restaurantAddress.getContactNo());
			restaurantAddressResponseDto.setPincode(restaurantAddress.getPinCode());
			restaurantAddressResponseDto.setRestaurantId(restaurantAddress.getRestaurant().getId());
			restaurantAddressResponseDto.setState(restaurantAddress.getState());
			restaurantAddressResponseDto.setStreet(restaurantAddress.getStreet());
			restaurantAddressResponseDto.setLatitude(restaurantAddress.getLatitude());
			restaurantAddressResponseDto.setLongitude(restaurantAddress.getLongitude());
			responseList.add(restaurantAddressResponseDto);

		}
		return responseList;
	}

}