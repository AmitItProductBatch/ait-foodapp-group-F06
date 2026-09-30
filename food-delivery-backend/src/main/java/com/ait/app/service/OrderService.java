package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.OrderHistoryResponseDto;
import com.ait.app.dto.OrderRequestDto;
import com.ait.app.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto CreateOrder(OrderRequestDto orderRequestDto);

	OrderResponseDto getOrderDetails(int orderId);

	List<OrderHistoryResponseDto> getOrderHistory(int userId);

	OrderResponseDto updateOrder(int orderId, OrderRequestDto orderRequestDto);

}
