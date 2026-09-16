package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CartDto;
import com.ait.app.dto.CartItemResponseDto;
import com.ait.app.dto.CartResponseDto;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.exception.UserNotFoundException;
import com.ait.app.model.Cart;
import com.ait.app.model.FoodItem;
import com.ait.app.model.Restaurant;
import com.ait.app.model.User;
import com.ait.app.repository.CartRepository;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.repository.RestaurantRepository;
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
	@Autowired
	RestaurantRepository restaurantRepository;

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
		Restaurant restaurant=restaurantRepository.findById(cartDto.getRestaurantId()).get();
		cart.setFoodItem(foodItem);
		cart.setFoodName(cartDto.getFoodName());
		cart.setPrice(cartDto.getPrice());
		cart.setQuantity(cartDto.getQuantity());
		cart.setUser(user);
		cart.setRestaurant(restaurant);

		Cart savedCart = cartRepository.save(cart);

		CartDto dto = new CartDto();
		dto.setFoodItemId(savedCart.getFoodItem().getId());
		dto.setFoodName(savedCart.getFoodName());
		dto.setPrice(savedCart.getPrice());
		dto.setQuantity(savedCart.getQuantity());
		dto.setUserId(savedCart.getUser().getId());
		dto.setRestaurantId(savedCart.getRestaurant().getId());
		return dto;

	}

	@Override
	public CartResponseDto getCart(int userId) {
		List<Cart> cartList = cartRepository.findByUserId(userId);
		

		CartResponseDto cartResponseDto = new CartResponseDto();
		List<CartItemResponseDto> itemList = new ArrayList();
		int totalAmount = 0;

		for (Cart c : cartList) {

			CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
			cartItemResponseDto.setCartItemId(c.getId());
			cartItemResponseDto.setFoodItemId(c.getFoodItem().getId());
			cartItemResponseDto.setFoodName(c.getFoodName());
			cartItemResponseDto.setPrice(c.getPrice());
			cartItemResponseDto.setQuantity(c.getQuantity());

			int total = c.getPrice() * c.getQuantity();
			cartItemResponseDto.setTotal(total);

			itemList.add(cartItemResponseDto);
			totalAmount = totalAmount + total;
			cartResponseDto.setCartId(c.getId());
			if (c.getRestaurant() != null) {

				cartResponseDto.setRestaurantId(c.getRestaurant().getId());
			}
			cartResponseDto.setItems(itemList);
			cartResponseDto.setTotalAmount(totalAmount);

		}
		return cartResponseDto;

	}

}
