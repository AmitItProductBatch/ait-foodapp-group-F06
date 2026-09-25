package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.DeliveryFeeRuleRequestDto;
import com.ait.app.dto.DeliveryFeeRuleResponseDto;
import com.ait.app.service.DeliveryFeeRuleService;

@RestController
@RequestMapping("api/prices")
@Validated
public class DeliveryFeeRuleController {

	@Autowired
	DeliveryFeeRuleService deliveryFeeRuleService;

	@PostMapping("addDeliveryRule")
	ResponseEntity addDeliveryRule(@RequestBody DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto) {

		DeliveryFeeRuleResponseDto deliveryFeeRuleResponseDto = deliveryFeeRuleService
				.createDeliveryFeeRule(deliveryFeeRuleRequestDto);
		return new ResponseEntity(deliveryFeeRuleResponseDto, HttpStatus.CREATED);
	}

	@PutMapping("deliveryRules")
	ResponseEntity<DeliveryFeeRuleResponseDto> updateDeliveryFeeRule(
			@RequestBody DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto) {

		DeliveryFeeRuleResponseDto deliveryFeeRuleResponseDto = deliveryFeeRuleService
				.updateDeliveryFeeRule(deliveryFeeRuleRequestDto);
		return new ResponseEntity(deliveryFeeRuleResponseDto, HttpStatus.OK);

	}
}
