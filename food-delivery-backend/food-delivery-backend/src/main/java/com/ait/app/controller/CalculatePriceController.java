package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.PriceCalculationRequestDto;
import com.ait.app.dto.PriceCalculationResponseDto;
import com.ait.app.service.PriceService;

@RestController
public class CalculatePriceController {
	@Autowired
	PriceService priceService;

	@PostMapping("calculate")
	ResponseEntity calculatePrice(@RequestBody PriceCalculationRequestDto priceCalculationRequestDto) {

		PriceCalculationResponseDto priceCalculationResponseDto = priceService
				.calculatePrice(priceCalculationRequestDto);
		return new ResponseEntity(priceCalculationResponseDto, HttpStatus.OK);
	}
}
