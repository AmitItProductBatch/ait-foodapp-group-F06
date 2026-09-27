package com.ait.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.AvailabilityStatus;
import com.ait.app.model.DeliveryPartner;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

	List<DeliveryPartner> findByActiveTrueAndAvailabilityStatus(AvailabilityStatus availabilityStatus);
}
