package com.ait.app.service;

import com.ait.app.dto.OrderTotalRequestDto;
import com.ait.app.dto.OrderTotalResponseDto;

public interface OrderTotalService {

	OrderTotalResponseDto calculateOrderTotal(OrderTotalRequestDto request);

}