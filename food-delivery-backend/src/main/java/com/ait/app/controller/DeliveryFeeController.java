package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.DeliveryFeeRequestDto;
import com.ait.app.dto.DeliveryFeeResponseDto;
import com.ait.app.service.DeliveryFeeService;

@RestController
@RequestMapping("api/prices")
public class DeliveryFeeController {

	@Autowired
	DeliveryFeeService deliveryFeeService;

	@PostMapping("deliveryFee")
	public ResponseEntity<DeliveryFeeResponseDto> calculateDeliveryFee(
			@RequestBody DeliveryFeeRequestDto deliveryFeeRequestDto) {

		DeliveryFeeResponseDto deliveryFeeResponseDto = deliveryFeeService.calculateDeliveryFee(deliveryFeeRequestDto);

		return new ResponseEntity(deliveryFeeResponseDto, HttpStatus.OK);
	}

}
