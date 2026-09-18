package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.PriceCalculationRequestDto;
import com.ait.app.dto.PriceCalculationResponseDto;
import com.ait.app.exception.FoodItemNotFoundException;
import com.ait.app.model.FoodItem;
import com.ait.app.repository.FoodItemRepository;
import com.ait.app.service.PriceService;
@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	FoodItemRepository foodItemRepository;

	@Override
	public PriceCalculationResponseDto calculatePrice(PriceCalculationRequestDto priceCalculationRequestDto) {

		Optional<FoodItem> o = foodItemRepository.findById(priceCalculationRequestDto.getFoodItemId());
		if (o.isEmpty()) {
			throw new FoodItemNotFoundException("food item not found", HttpStatus.NOT_FOUND);
		}
		FoodItem foodItem = o.get();
		int unitPrice = foodItem.getPrice();
		int total = unitPrice + priceCalculationRequestDto.getQuantity();

		PriceCalculationResponseDto priceCalculationResponseDto = new PriceCalculationResponseDto();
		priceCalculationResponseDto.setFoodItemId(foodItem.getId());
		priceCalculationResponseDto.setQuantity(priceCalculationRequestDto.getQuantity());
		priceCalculationResponseDto.setTotal(total);
		priceCalculationResponseDto.setUnitPrice(unitPrice);

		return priceCalculationResponseDto;
	}

}
