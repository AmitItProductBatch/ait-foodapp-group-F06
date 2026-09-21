package com.ait.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ait.app.dto.RestaurantDto;
import com.ait.app.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	@Query("""
			    SELECT fc.restaurant
			    FROM FoodCategory fc
			    WHERE fc.id = :categoryId
			""")
	List<Restaurant> findAllRestaurantsByCategoryId(@Param("categoryId") int categoryId);
}
