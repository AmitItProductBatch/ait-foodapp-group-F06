package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ait.app.dto.OrderItemRequestDto;
import com.ait.app.dto.OrderItemResponseDto;
import com.ait.app.service.OrderItemService;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@PostMapping("/create")
	public ResponseEntity<OrderItemResponseDto> createOrderItem(@RequestBody OrderItemRequestDto orderItemRequestDto) {

		OrderItemResponseDto response = orderItemService.createOrderItem(orderItemRequestDto);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}
}