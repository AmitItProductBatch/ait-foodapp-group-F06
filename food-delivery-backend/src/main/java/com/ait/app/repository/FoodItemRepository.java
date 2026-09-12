package com.ait.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

	 List<FoodItem>findAllFoodItemByRestaurantId(int restaurantId);
}
