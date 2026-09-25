package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.DeliveryFeeRuleRequestDto;
import com.ait.app.dto.DeliveryFeeRuleResponseDto;
import com.ait.app.exception.DeliveryFeeRuleNotFoundException;
import com.ait.app.model.DeliveryFeeRule;
import com.ait.app.repository.DeliveryFeeRuleRepository;
import com.ait.app.service.DeliveryFeeRuleService;

@Service
public class DeliveryFeeRuleServiceImpl implements DeliveryFeeRuleService {
	@Autowired
	private DeliveryFeeRuleRepository deliveryFeeRuleRepository;

	@Override
	public DeliveryFeeRuleResponseDto updateDeliveryFeeRule(DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto) {
		Optional<DeliveryFeeRule> o = deliveryFeeRuleRepository.findByActiveTrue();
		if (o.isEmpty()) {
			throw new DeliveryFeeRuleNotFoundException("delivery fee rules not found", HttpStatus.NOT_FOUND);
		}
		DeliveryFeeRule rule = o.get();

		rule.setBaseFee(deliveryFeeRuleRequestDto.getBaseFee());
		rule.setFreeDeliveryThreshold(deliveryFeeRuleRequestDto.getFreeDeliveryThreshold());
		rule.setMaxDeliveryRadius(deliveryFeeRuleRequestDto.getMaxDeliveryRadius());
		rule.setPerKmRate(deliveryFeeRuleRequestDto.getPerKmRate());
		rule.setActive(true);
		DeliveryFeeRule savedRule = deliveryFeeRuleRepository.save(rule);

		DeliveryFeeRuleResponseDto deliveryFeeRuleResponseDto = new DeliveryFeeRuleResponseDto();
		deliveryFeeRuleResponseDto.setActive(savedRule.isActive());
		deliveryFeeRuleResponseDto.setBaseFee(savedRule.getBaseFee());
		deliveryFeeRuleResponseDto.setFreeDeliveryThreshold(savedRule.getFreeDeliveryThreshold());
		deliveryFeeRuleResponseDto.setId(savedRule.getId());
		deliveryFeeRuleResponseDto.setMaxDeliveryRadius(savedRule.getMaxDeliveryRadius());
		deliveryFeeRuleResponseDto.setPerKmRate(savedRule.getPerKmRate());

		return deliveryFeeRuleResponseDto;
	}

	@Override
	public DeliveryFeeRuleResponseDto createDeliveryFeeRule(DeliveryFeeRuleRequestDto deliveryFeeRuleRequestDto) {

		DeliveryFeeRule deliveryFeeRule = new DeliveryFeeRule();
		deliveryFeeRule.setActive(true);
		deliveryFeeRule.setBaseFee(deliveryFeeRuleRequestDto.getBaseFee());
		deliveryFeeRule.setFreeDeliveryThreshold(deliveryFeeRuleRequestDto.getFreeDeliveryThreshold());
		deliveryFeeRule.setMaxDeliveryRadius(deliveryFeeRuleRequestDto.getMaxDeliveryRadius());
		deliveryFeeRule.setPerKmRate(deliveryFeeRuleRequestDto.getPerKmRate());
		DeliveryFeeRule rule = deliveryFeeRuleRepository.save(deliveryFeeRule);

		DeliveryFeeRuleResponseDto deliveryFeeRuleResponseDto = new DeliveryFeeRuleResponseDto();
		deliveryFeeRuleResponseDto.setActive(rule.isActive());
		deliveryFeeRuleResponseDto.setBaseFee(rule.getBaseFee());
		deliveryFeeRuleResponseDto.setFreeDeliveryThreshold(rule.getFreeDeliveryThreshold());
		deliveryFeeRuleResponseDto.setMaxDeliveryRadius(rule.getMaxDeliveryRadius());
		deliveryFeeRuleResponseDto.setPerKmRate(rule.getPerKmRate());
		deliveryFeeRuleResponseDto.setId(rule.getId());

		return deliveryFeeRuleResponseDto;
	}

}