package com.ait.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DeliveryFeeRule {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private BigDecimal baseFee;

private BigDecimal perKmRate;

private BigDecimal maxDeliveryRadius;

private BigDecimal freeDeliveryThreshold;

private boolean active;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public BigDecimal getBaseFee() {
	return baseFee;
}

public void setBaseFee(BigDecimal baseFee) {
	this.baseFee = baseFee;
}

public BigDecimal getPerKmRate() {
	return perKmRate;
}

public void setPerKmRate(BigDecimal perKmRate) {
	this.perKmRate = perKmRate;
}

public BigDecimal getMaxDeliveryRadius() {
	return maxDeliveryRadius;
}

public void setMaxDeliveryRadius(BigDecimal maxDeliveryRadius) {
	this.maxDeliveryRadius = maxDeliveryRadius;
}

public BigDecimal getFreeDeliveryThreshold() {
	return freeDeliveryThreshold;
}

public void setFreeDeliveryThreshold(BigDecimal freeDeliveryThreshold) {
	this.freeDeliveryThreshold = freeDeliveryThreshold;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

}
