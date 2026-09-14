package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CartDto;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.exception.UserNotFoundException;
import com.ait.app.model.Cart;
import com.ait.app.model.FoodItem;
import com.ait.app.model.User;
import com.ait.app.repository.CartRepository;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.repository.UserRepository;
import com.ait.app.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	FoodItemRepository foodItemRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;

	@Override
	public CartDto createCart(CartDto cartDto) {
		Cart cart = new Cart();
		Optional<FoodItem> o = foodItemRepository.findById(cartDto.getFoodItemId());
		if (o.isEmpty()) {
			throw new FoodItemNotFoundException("food item not found with id :" + cartDto.getFoodItemId(),
					HttpStatus.NOT_FOUND);
		}
		FoodItem foodItem = o.get();

		Optional<User> optional = userRepository.findById(cartDto.getUserId());
		if (optional.isEmpty()) {
			throw new UserNotFoundException("user not found with id " + cartDto.getUserId(), HttpStatus.NOT_FOUND);
		}
		User user = optional.get();
		cart.setFoodItem(foodItem);
		cart.setFoodName(cartDto.getFoodName());
		cart.setPrice(cartDto.getPrice());
		cart.setQuantity(cartDto.getQuantity());
		cart.setUser(user);
		Cart savedCart = cartRepository.save(cart);

		CartDto dto = new CartDto();
		dto.setFoodItemId(savedCart.getFoodItem().getId());
		dto.setFoodName(savedCart.getFoodName());
		dto.setPrice(savedCart.getPrice());
		dto.setQuantity(savedCart.getQuantity());
		dto.setUserId(savedCart.getUser().getId());
		return dto;

	}

}
