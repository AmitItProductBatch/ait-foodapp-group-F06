package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.DeliveryPartnerAvailabilityRequestDto;
import com.ait.app.dto.DeliveryPartnerRequestDto;
import com.ait.app.dto.DeliveryPartnerResponseDto;
import com.ait.app.model.DeliveryPartner;

public interface DeliveryPartnerService {
	DeliveryPartnerResponseDto addDeliveryPartner(DeliveryPartnerRequestDto deliveryPartnerRequestDto);

	DeliveryPartnerResponseDto getDeliveryPartner(int deliveryPartnerId);

	List<DeliveryPartnerResponseDto> getAllDeliveryPartner();

	DeliveryPartnerResponseDto updateDeliveryPartner(int deliveryPartnerId,
			DeliveryPartnerRequestDto deliveryPartnerRequestDto);

	void deleteDeliveryPartner(int deliveryPartnerId);

	DeliveryPartnerResponseDto updateDeliveryPartnerAvailability(int deliveryPartnerId,
			DeliveryPartnerAvailabilityRequestDto deliveryPartnerAvailabilityRequestDto);

List<DeliveryPartnerResponseDto>getAvailableDeliveryPartner();




}
