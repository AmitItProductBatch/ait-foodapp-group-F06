package com.ait.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.RestaurantAddress;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Integer> {

	Optional<RestaurantAddress> findByRestaurantId(int restaurantId);
}