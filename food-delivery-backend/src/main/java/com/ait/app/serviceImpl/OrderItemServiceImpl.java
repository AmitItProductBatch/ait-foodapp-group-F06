package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.OrderItemRequestDto;
import com.ait.app.dto.OrderItemResponseDto;
import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.exception.OrderNotFoundException;
import com.ait.app.model.FoodItem;
import com.ait.app.model.Order;
import com.ait.app.model.OrderItem;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.repository.OrderItemRepository;
import com.ait.app.repository.OrderRepository;
import com.ait.app.service.OrderItemService;
import com.ait.app.service.OrderService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto) {

		Optional<Order> optionalOrder = orderRepository.findById(orderItemRequestDto.getOrderId());

		if (optionalOrder.isEmpty()) {
			throw new OrderNotFoundException("Order not found with id " + orderItemRequestDto.getOrderId(),
					HttpStatus.NOT_FOUND);
		}

		Order order = optionalOrder.get();

		Optional<FoodItem> optionalFoodItem = foodItemRepository.findById(orderItemRequestDto.getFoodItemId());

		if (optionalFoodItem.isEmpty()) {
			throw new FoodItemNotFoundException("Food item not found with id " + orderItemRequestDto.getFoodItemId(),
					HttpStatus.NOT_FOUND);
		}

		FoodItem foodItem = optionalFoodItem.get();

		OrderItem orderItem = new OrderItem();

		orderItem.setOrder(order);
		orderItem.setFoodItem(foodItem);

		orderItem.setQuantity(orderItemRequestDto.getQuantity());
		orderItem.setUnitPrice(orderItemRequestDto.getUnitPrice());

		double totalPrice = orderItemRequestDto.getQuantity() * orderItemRequestDto.getUnitPrice();

		orderItem.setTotalPrice(totalPrice);

		OrderItem savedOrderItem = orderItemRepository.save(orderItem);

		OrderItemResponseDto response = new OrderItemResponseDto();

		response.setOrderItemId(savedOrderItem.getId());
		response.setFoodItemId(savedOrderItem.getFoodItem().getName());
		response.setQuantity(savedOrderItem.getQuantity());
		response.setUnitPrice(savedOrderItem.getUnitPrice());
		response.setTotalPrice(savedOrderItem.getTotalPrice());
		

		return response;
	}
}
