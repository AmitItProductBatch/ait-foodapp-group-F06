package com.ait.app.service;


import com.ait.app.dto.DeliveryFeeRuleRequestDto;
import com.ait.app.dto.DeliveryFeeRuleResponseDto;

public interface DeliveryFeeRuleService {
	DeliveryFeeRuleResponseDto updateDeliveryFeeRule(DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto);
	DeliveryFeeRuleResponseDto createDeliveryFeeRule(DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto);
}
