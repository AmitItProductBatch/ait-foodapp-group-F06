package com.ait.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer>{
	
	boolean existsByNameAndRestaurantId(String name, int restaurantId);

}
