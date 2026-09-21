package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.OrderTotalRequestDto;
import com.ait.app.dto.OrderTotalResponseDto;
import com.ait.app.service.OrderTotalService;

@RestController
@RequestMapping("api/prices")
public class OrderTotalController {

	@Autowired
	private OrderTotalService orderTotalService;

	@PostMapping("/orderTotal")
	public ResponseEntity<OrderTotalResponseDto> calculateOrderTotal(@RequestBody OrderTotalRequestDto request) {

		OrderTotalResponseDto response = orderTotalService.calculateOrderTotal(request);

		return new ResponseEntity(response, HttpStatus.OK);
	}
}