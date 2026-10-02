package com.ait.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ait.app.dto.OrderHistoryResponseDto;
import com.ait.app.dto.OrderItemResponseDto;
import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;
import com.ait.app.exception.AddressNotFoundException;
import com.ait.app.exception.CartNotFoundException;
import com.ait.app.exception.OrderNotFoundException;
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
	@Transactional
	public OrderResponseDto CreateOrder(OrderRequestDto orderRequestDto) {
		Optional<User> o = userRepository.findById(orderRequestDto.getUserId());
		if (o.isEmpty()) {
			throw new UserNotFoundException("user not found exception", HttpStatus.NOT_FOUND);
		}

		User user = o.get();

		List<Cart> cartList = cartRepository.findByUserId(user.getId());

		if (cartList.isEmpty()) {
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
		List<OrderItem> orderItems = new ArrayList();

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
		// cartRepository.deleteAll(cartList);

		OrderResponseDto response = new OrderResponseDto();

		response.setOrderId(savedOrder.getId());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setStatus(savedOrder.getStatus());
		response.setAddressId(savedOrder.getAddress().getId());
		response.setRestaurantId(savedOrder.getRestaurant().getId());
		response.setUserId(savedOrder.getUser().getId());
		List<OrderItemResponseDto> itemResponseList = new ArrayList();
		for (OrderItem orderItem : savedOrder.getOrderItems()) {

			OrderItemResponseDto itemDto = new OrderItemResponseDto();
			itemDto.setFoodItemId(orderItem.getFoodItem().getName());
			itemDto.setOrderItemId(orderItem.getId());
			itemDto.setQuantity(orderItem.getQuantity());
			itemDto.setTotalPrice(orderItem.getTotalPrice());
			itemDto.setUnitPrice(orderItem.getUnitPrice());
			itemResponseList.add(itemDto);
		}
		response.setItems(itemResponseList);

		return response;
	}

	@Override
	public OrderResponseDto getOrderDetails(int orderId) {
		Optional<Order> o = orderRepository.findById(orderId);

		if (o.isEmpty()) {

			throw new OrderNotFoundException("order is not there ", HttpStatus.NOT_FOUND);
		}
		Order order = o.get();
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setAddressId(order.getAddress().getId());

		orderResponseDto.setOrderId(order.getId());
		orderResponseDto.setRestaurantId(order.getRestaurant().getId());
		orderResponseDto.setStatus(order.getStatus());
		orderResponseDto.setTotalAmount(order.getTotalAmount());
		orderResponseDto.setUserId(order.getUser().getId());
		List<OrderItem> list = orderItemRepository.findByOrderId(orderId);
		List<OrderItemResponseDto> itemResponseList = new ArrayList<>();
		for (OrderItem orderItem : list) {
			OrderItemResponseDto dto = new OrderItemResponseDto();
			dto.setFoodItemId(orderItem.getFoodItem().getName());
			dto.setOrderItemId(orderItem.getId());
			dto.setQuantity(orderItem.getQuantity());

			dto.setUnitPrice(orderItem.getUnitPrice());
			double itemTotal = orderItem.getUnitPrice() * orderItem.getQuantity();
			dto.setTotalPrice(orderItem.getTotalPrice());
			itemResponseList.add(dto);
			orderResponseDto.setItems(itemResponseList);

		}
		return orderResponseDto;
	}

	@Override
	public List<OrderHistoryResponseDto> getOrderHistory(int userId) {
		List<Order> list = orderRepository.findByUserId(userId);

		List<OrderHistoryResponseDto> l = new ArrayList<>();
		for (Order order : list) {

			OrderHistoryResponseDto dto = new OrderHistoryResponseDto();
			dto.setOrderId(order.getId());
			dto.setOrderTime(order.getOrderTime());
			dto.setPaymentMethod(order.getPaymentMethod());
			dto.setRestaurantId(order.getRestaurant().getId());
			dto.setStatus(order.getStatus());
			dto.setTotalAmount(order.getTotalAmount());
			dto.setUserId(order.getUser().getId());
			List<Integer> orderItemIds = new ArrayList<>();

			for (OrderItem orderItem : order.getOrderItems()) {
				orderItemIds.add(orderItem.getId());
			}

			dto.setOrderItemId(orderItemIds);
			l.add(dto);
		}

		return l;
	}

	@Override
	public OrderResponseDto updateOrder(int orderId, OrderRequestDto orderRequestDto) {
		Optional<Order> o = orderRepository.findById(orderId);
		if (o.isEmpty()) {
			throw new OrderNotFoundException("order not found", HttpStatus.NOT_FOUND);
		}
		Order order = o.get();
		Optional<Address> optionalAddress = addressRepository.findById(orderRequestDto.getAddressId());
		if (optionalAddress.isEmpty()) {
			throw new AddressNotFoundException("address not found", HttpStatus.NOT_FOUND);
		}
		Address address = optionalAddress.get();
		Optional<Restaurant> optional = restaurantRepository.findById(orderRequestDto.getRestaurantId());
		if (optional.isEmpty()) {
			throw new RestaurantException("restaurant not available", HttpStatus.NOT_FOUND);

		}
		Restaurant restaurant = optional.get();
		Optional<User> optionalUser = userRepository.findById(orderRequestDto.getUserId());
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("user not found", HttpStatus.NOT_FOUND);
		}
		User user = optionalUser.get();
		order.setAddress(address);
		order.setDeliveryInstructions(orderRequestDto.getDeliveryInstructions());
		order.setPaymentMethod(orderRequestDto.getPaymentMethod());
		order.setRestaurant(restaurant);
		order.setTotalAmount(orderRequestDto.getTotalAmount());
		order.setUser(user);
		Order order2 = orderRepository.save(order);
		OrderResponseDto dto = new OrderResponseDto();
		dto.setAddressId(order2.getAddress().getId());

		dto.setOrderId(order2.getId());
		dto.setRestaurantId(order2.getRestaurant().getId());
		dto.setStatus(order2.getStatus());
		dto.setTotalAmount(order2.getTotalAmount());
		dto.setUserId(order2.getUser().getId());
		List<OrderItemResponseDto> items = new ArrayList<>();
		for (OrderItem orderItem : order2.getOrderItems()) {
			OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
			orderItemResponseDto.setOrderItemId(orderItem.getId());
			items.add(orderItemResponseDto);
		}

		return dto;
	}

}