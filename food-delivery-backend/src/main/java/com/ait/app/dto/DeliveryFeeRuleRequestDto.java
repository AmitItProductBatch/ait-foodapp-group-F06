package com.ait.app.dto;

import java.math.BigDecimal;

public class DeliveryFeeRuleRequestDto {
	private BigDecimal baseFee;

	private BigDecimal perKmRate;

	private BigDecimal maxDeliveryRadius;

	private BigDecimal freeDeliveryThreshold;

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

}
