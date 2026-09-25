package com.ait.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.DeliveryFeeRule;

public interface DeliveryFeeRuleRepository extends JpaRepository<DeliveryFeeRule, Integer> {
	Optional<DeliveryFeeRule> findByActiveTrue();
}
