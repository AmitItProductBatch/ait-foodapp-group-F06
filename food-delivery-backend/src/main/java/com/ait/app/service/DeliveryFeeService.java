package com.ait.app.service;

import com.ait.app.dto.DeliveryFeeRequestDto;
import com.ait.app.dto.DeliveryFeeResponseDto;

public interface DeliveryFeeService {

	DeliveryFeeResponseDto calculateDeliveryFee(DeliveryFeeRequestDto deliveryFeeRequestDto);

}
