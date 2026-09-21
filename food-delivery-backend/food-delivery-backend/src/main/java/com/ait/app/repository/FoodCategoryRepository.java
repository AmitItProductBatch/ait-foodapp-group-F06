package com.ait.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.model.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

	boolean existsByNameAndRestaurantId(String name, int restaurantId);

	boolean existsByNameAndRestaurantIdAndIdNot(String name, int restaurantId, int id);

	List<FoodCategory> findAllCategoriesByRestaurantId(int restaurantId);

	
}
