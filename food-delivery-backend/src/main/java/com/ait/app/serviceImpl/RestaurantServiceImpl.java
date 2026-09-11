package com.ait.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.exception.RestaurantException;
import com.ait.app.exception.UserNotFoundException;
import com.ait.app.model.Restaurant;
import com.ait.app.model.User;
import com.ait.app.repository.RestaurantRepository;
import com.ait.app.repository.UserRepository;
import com.ait.app.service.RestaurantService;
@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
		User user=userRepository.findById(restaurantDto.getUserId()).get();
		if(user.getName().isBlank()){
			throw new UserNotFoundException("user not found ", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant=new Restaurant();
		restaurant.setName(restaurantDto.getName());
		restaurant.setAddress(restaurantDto.getAddress());
		restaurant.setCuisine(restaurantDto.getCuisine());
		restaurant.setMobileNo(restaurantDto.getMobileNo());
		restaurant.setRating(restaurantDto.getRating());
		restaurant.setUser(user);
		
		Restaurant savedRestaurant= restaurantRepository.save(restaurant);
		RestaurantDto  dto=new RestaurantDto();
		dto.setName(savedRestaurant.getName());
		dto.setCuisine(savedRestaurant.getCuisine());
		dto.setAddress(savedRestaurant.getAddress());
		dto.setMobileNo(savedRestaurant.getMobileNo());
		dto.setRating(savedRestaurant.getRating());
		dto.setUserId(savedRestaurant.getUser().getId());
		return dto;
		
	}

	@Override
	public List<Restaurant> GetAllRestaurant() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}

}
