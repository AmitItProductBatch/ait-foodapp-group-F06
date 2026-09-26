package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.DeliveryPartnerRequestDto;
import com.ait.app.dto.DeliveryPartnerResponseDto;

public interface DeliveryPartnerService {
	DeliveryPartnerResponseDto addDeliveryPartner(DeliveryPartnerRequestDto deliveryPartnerRequestDto);

	DeliveryPartnerResponseDto getDeliveryPartner(int deliveryPartnerId);

	List<DeliveryPartnerResponseDto> getAllDeliveryPartner();

	DeliveryPartnerResponseDto updateDeliveryPartner(int deliveryPartnerId,
			DeliveryPartnerRequestDto deliveryPartnerRequestDto);

	void deleteDeliveryPartner(int deliveryPartnerId);
}
