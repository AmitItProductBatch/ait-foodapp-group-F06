package com.ait.app.service;

import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto CreateOrder(OrderRequestDto orderRequestDto);

}
