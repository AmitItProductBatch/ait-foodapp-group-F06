package com.ait.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;
import com.ait.app.exception.AddressNotFoundException;
import com.ait.app.exception.CartNotFoundException;
import com.ait.app.exception.RestaurantException;
import com.ait.app.exception.UserNotFoundException;
import com.ait.app.model.Address;
import com.ait.app.model.Cart;
import com.ait.app.model.Order;
import com.ait.app.model.OrderItem;
import com.ait.app.model.Restaurant;
import com.ait.app.model.User;
import com.ait.app.repository.AddressRepository;
import com.ait.app.repository.CartRepository;
import com.ait.app.repository.OrderItemRepository;
import com.ait.app.repository.OrderRepository;
import com.ait.app.repository.RestaurantRepository;
import com.ait.app.repository.UserRepository;
import com.ait.app.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public OrderResponseDto CreateOrder(OrderRequestDto orderRequestDto) {
		Optional<User> o = userRepository.findById(orderRequestDto.getUserId());
		if (o.isEmpty()) {
			throw new UserNotFoundException("user not found exception", HttpStatus.NOT_FOUND);
		}

		User user = o.get();

		List<Cart> cartList = cartRepository.findByUserId(user.getId());

		if ( cartList.isEmpty()) {
			throw new CartNotFoundException("Cart is empty", HttpStatus.NOT_FOUND);
		}

		Optional<Address> addressOptional = addressRepository.findById(orderRequestDto.getAddressId());
		if (addressOptional.isEmpty()) {
			throw new AddressNotFoundException("address not found", HttpStatus.NOT_FOUND);
		}

		Address address = addressOptional.get();

		Optional<Restaurant> optional = restaurantRepository.findById(orderRequestDto.getRestaurantId());
		if (optional.isEmpty()) {

			throw new RestaurantException("restaurant not present", HttpStatus.NOT_FOUND);
		}
		Restaurant restaurant = optional.get();
		double totalAmount = 0;

		for (Cart cart : cartList) {
			totalAmount += cart.getPrice() * cart.getQuantity();
		}

		Order order = new Order();

		order.setUser(user);
		order.setRestaurant(restaurant);
		order.setAddress(address);
		order.setPaymentMethod(orderRequestDto.getPaymentMethod());

		order.setStatus("PLACED");
		order.setOrderTime(LocalDateTime.now());

		order.setDeliveryInstructions(orderRequestDto.getDeliveryInstructions());
		order.setTotalAmount(totalAmount);
		


		Order savedOrder = orderRepository.save(order);
		List<OrderItem> orderItems = new ArrayList<>();

		for (Cart cart : cartList) {

			OrderItem orderItem = new OrderItem();

			orderItem.setOrder(savedOrder);
			orderItem.setFoodItem(cart.getFoodItem());
			orderItem.setFoodName(cart.getFoodName());
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setUnitPrice(cart.getPrice());
			orderItem.setTotalPrice(cart.getPrice() * cart.getQuantity());

			orderItems.add(orderItem);
		}

		orderItemRepository.saveAll(orderItems);
	//	 cartRepository.deleteAll(cartList);

		OrderResponseDto response = new OrderResponseDto();

		response.setOrderId(savedOrder.getId());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setStatus(savedOrder.getStatus());

		return response;
	}
}