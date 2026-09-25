package com.ait.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.DeliveryPartnerRequestDto;
import com.ait.app.dto.DeliveryPartnerResponseDto;
import com.ait.app.exception.DeliveryPartnerNotFoundException;
import com.ait.app.model.DeliveryPartner;
import com.ait.app.repository.DeliveryPartnerRepository;
import com.ait.app.service.DeliveryPartnerService;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
	@Autowired
	DeliveryPartnerRepository deliveryPartnerRepository;

	@Override
	public DeliveryPartnerResponseDto addDeliveryPartner(DeliveryPartnerRequestDto deliveryPartnerRequestDto) {

		DeliveryPartner deliveryPartner = new DeliveryPartner();
		deliveryPartner.setActive(true);
		deliveryPartner.setCreatedAt(LocalDateTime.now());
		deliveryPartner.setUpdatedAt(LocalDateTime.now());
		deliveryPartner.setEmail(deliveryPartnerRequestDto.getEmail());
		deliveryPartner.setMobileNo(deliveryPartnerRequestDto.getMobileNo());
		deliveryPartner.setName(deliveryPartnerRequestDto.getName());
		deliveryPartner.setStatus("OFFLINE");
		deliveryPartner.setVechicleType(deliveryPartnerRequestDto.getVechicleType());
		DeliveryPartner partner = deliveryPartnerRepository.save(deliveryPartner);

		DeliveryPartnerResponseDto deliveryPartnerResponseDto = new DeliveryPartnerResponseDto();
		deliveryPartnerResponseDto.setActive(partner.isActive());
		deliveryPartnerResponseDto.setCreatedAt(partner.getCreatedAt());
		deliveryPartnerResponseDto.setDeliveryPartnerId(partner.getId());
		deliveryPartnerResponseDto.setEmail(partner.getEmail());
		deliveryPartnerResponseDto.setMobileNo(partner.getMobileNo());
		deliveryPartnerResponseDto.setName(partner.getName());
		deliveryPartnerResponseDto.setStatus(partner.getStatus());
		deliveryPartnerResponseDto.setUpdatedAt(partner.getUpdatedAt());
		deliveryPartnerResponseDto.setVechicleType(partner.getVechicleType());
		return deliveryPartnerResponseDto;
	}

	@Override
	public DeliveryPartnerResponseDto getDeliveryPartner(int deliveryPartnerId) {
		Optional<DeliveryPartner> o = deliveryPartnerRepository.findById(deliveryPartnerId);
		if (o.isEmpty()) {
			throw new DeliveryPartnerNotFoundException("delivery partner not found", HttpStatus.NOT_FOUND);
		}
		DeliveryPartner deliveryPartner = o.get();
		DeliveryPartnerResponseDto deliveryPartnerResponseDto = new DeliveryPartnerResponseDto();
		deliveryPartnerResponseDto.setActive(deliveryPartner.isActive());
		deliveryPartnerResponseDto.setCreatedAt(deliveryPartner.getCreatedAt());
		deliveryPartnerResponseDto.setDeliveryPartnerId(deliveryPartner.getId());
		deliveryPartnerResponseDto.setEmail(deliveryPartner.getEmail());
		deliveryPartnerResponseDto.setMobileNo(deliveryPartner.getMobileNo());
		deliveryPartnerResponseDto.setName(deliveryPartner.getName());
		deliveryPartnerResponseDto.setStatus(deliveryPartner.getStatus());
		deliveryPartnerResponseDto.setUpdatedAt(deliveryPartner.getUpdatedAt());
		deliveryPartnerResponseDto.setVechicleType(deliveryPartner.getVechicleType());

		return deliveryPartnerResponseDto;
	}

	@Override
	public List<DeliveryPartnerResponseDto> getAllDeliveryPartner() {
		List<DeliveryPartner> list = deliveryPartnerRepository.findAll();
		List<DeliveryPartnerResponseDto> deliveryPartnerList = new ArrayList();
		for (DeliveryPartner deliveryPartner : list) {
			DeliveryPartnerResponseDto deliveryPartnerResponseDto = new DeliveryPartnerResponseDto();
			deliveryPartnerResponseDto.setActive(deliveryPartner.isActive());
			deliveryPartnerResponseDto.setCreatedAt(deliveryPartner.getCreatedAt());
			deliveryPartnerResponseDto.setDeliveryPartnerId(deliveryPartner.getId());
			deliveryPartnerResponseDto.setEmail(deliveryPartner.getEmail());
			deliveryPartnerResponseDto.setMobileNo(deliveryPartner.getMobileNo());
			deliveryPartnerResponseDto.setName(deliveryPartner.getName());
			deliveryPartnerResponseDto.setStatus(deliveryPartner.getStatus());
			deliveryPartnerResponseDto.setUpdatedAt(deliveryPartner.getUpdatedAt());
			deliveryPartnerResponseDto.setVechicleType(deliveryPartner.getVechicleType());
			deliveryPartnerList.add(deliveryPartnerResponseDto);
		}
		return deliveryPartnerList;
	}

}
