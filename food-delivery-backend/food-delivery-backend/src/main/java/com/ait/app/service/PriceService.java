package com.ait.app.service;

import com.ait.app.dto.PriceCalculationRequestDto;
import com.ait.app.dto.PriceCalculationResponseDto;

public interface PriceService {
	PriceCalculationResponseDto calculatePrice(PriceCalculationRequestDto priceCalculationRequestDto);
}
