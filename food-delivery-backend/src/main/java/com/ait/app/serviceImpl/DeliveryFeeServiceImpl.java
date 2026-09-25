package com.ait.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.DeliveryFeeRequestDto;
import com.ait.app.dto.DeliveryFeeResponseDto;
import com.ait.app.exception.AddressNotFoundException;
import com.ait.app.exception.DeliveryFeeRuleNotFoundException;
import com.ait.app.exception.DeliveryRadiusExceededException;
import com.ait.app.exception.RestaurantAddressNotFoundException;
import com.ait.app.model.Address;
import com.ait.app.model.DeliveryFeeRule;
import com.ait.app.model.RestaurantAddress;
import com.ait.app.repository.AddressRepository;
import com.ait.app.repository.DeliveryFeeRuleRepository;
import com.ait.app.repository.RestaurantAddressRepository;
import com.ait.app.service.DeliveryFeeService;

@Service
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private DeliveryFeeRuleRepository deliveryFeeRuleRepository;

	@Override
	public DeliveryFeeResponseDto calculateDeliveryFee(DeliveryFeeRequestDto deliveryFeeRequestDto) {

		Optional<RestaurantAddress> o = restaurantAddressRepository
				.findById(deliveryFeeRequestDto.getRestaurantAddressId());
		if (o.isEmpty()) {
			throw new RestaurantAddressNotFoundException("address not found", HttpStatus.NOT_FOUND);

		}

		RestaurantAddress restaurantAddress = o.get();

		Optional<Address> optional = addressRepository.findById(deliveryFeeRequestDto.getAddressId());
		if (optional.isEmpty()) {
			throw new AddressNotFoundException("address not found", HttpStatus.NOT_FOUND);
		}

		Address address = optional.get();
		Optional<DeliveryFeeRule> ruleOptional = deliveryFeeRuleRepository.findByActiveTrue();

		if (ruleOptional.isEmpty()) {
			throw new DeliveryFeeRuleNotFoundException("Delivery fee rule not found", HttpStatus.NOT_FOUND);
		}

		DeliveryFeeRule rule = ruleOptional.get();

		double restaurantAddressLat = restaurantAddress.getLatitude();
		double restaurantAddressLon = restaurantAddress.getLongitude();

		double addressLat = address.getLatitude();
		double addressLon = address.getLongitude();

		double distanceKm = calculateDistance(restaurantAddressLat, restaurantAddressLon, addressLat, addressLon);

		if (distanceKm > rule.getMaxDeliveryRadius().doubleValue()) {
			throw new DeliveryRadiusExceededException("Deilvery Address is OutSide the maximum delivery radius",
					HttpStatus.BAD_REQUEST);
		}

		double deliveryFee;
		if (deliveryFeeRequestDto.getOrderAmount().compareTo(rule.getFreeDeliveryThreshold()) >= 0) {

			deliveryFee = 0.0;

		} else {

			deliveryFee = rule.getBaseFee().doubleValue() + (distanceKm * rule.getPerKmRate().doubleValue());
		}
		DeliveryFeeResponseDto deliveryFeeResponseDto = new DeliveryFeeResponseDto();
		deliveryFeeResponseDto.setDistance(Math.round(distanceKm * 100.0) / 100.0);
		deliveryFeeResponseDto.setDeliveryFee(Math.round(deliveryFee * 100.0) / 100.0);

		return deliveryFeeResponseDto;
	}

	private double calculateDistance(double restaurantAddressLat, double restaurantAddressLon, double addressLat,
			double addressLon) {
		final int EARTH_RADIUS = 6371;

		double latDistance = Math.toRadians(addressLat - restaurantAddressLat);
		double lonDistance = Math.toRadians(addressLon - restaurantAddressLon);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(restaurantAddressLat)) * Math.cos(Math.toRadians(addressLat))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}

}
