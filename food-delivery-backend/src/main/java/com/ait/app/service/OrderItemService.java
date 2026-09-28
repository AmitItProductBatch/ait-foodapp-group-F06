package com.ait.app.service;

import com.ait.app.dto.OrderItemRequestDto;
import com.ait.app.dto.OrderItemResponseDto;

public interface OrderItemService {

	OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto);
}