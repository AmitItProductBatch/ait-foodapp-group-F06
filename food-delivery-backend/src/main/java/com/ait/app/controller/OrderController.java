package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ait.app.dto.OrderHistoryResponseDto;
import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;
import com.ait.app.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {

		OrderResponseDto response = orderService.CreateOrder(orderRequestDto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDto> getOrderDetails(@PathVariable int orderId) {

		OrderResponseDto response = orderService.getOrderDetails(orderId);

		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("orderHistory/{userId}")
	ResponseEntity getOrderHistory(@PathVariable int userId) {

		List<OrderHistoryResponseDto> l = orderService.getOrderHistory(userId);

		return new ResponseEntity(l, HttpStatus.OK);
	}
	@PutMapping("updateOrder/{orderId}")
	ResponseEntity updateOrder(@PathVariable int orderId,@RequestBody OrderRequestDto orderRequestDto) {
		
	OrderResponseDto orderResponseDto=	orderService.updateOrder(orderId, orderRequestDto);
	return new ResponseEntity(orderResponseDto,HttpStatus.OK);
	
	}

}