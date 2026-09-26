package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.DeliveryPartnerRequestDto;
import com.ait.app.dto.DeliveryPartnerResponseDto;
import com.ait.app.service.DeliveryPartnerService;

@RestController
@RequestMapping("api/dp")
public class DeliveryPartnerController {
	@Autowired
	DeliveryPartnerService deliveryPartnerService;

	@PostMapping("addDeliveryPartner")
	ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequestDto deliveryPartnerRequestDto) {

		DeliveryPartnerResponseDto deliveryPartnerResponseDto = deliveryPartnerService
				.addDeliveryPartner(deliveryPartnerRequestDto);

		return new ResponseEntity(deliveryPartnerResponseDto, HttpStatus.CREATED);

	}

	@GetMapping("getDeliveryPartner/{deliveryPartnerId}")
	ResponseEntity getDeliveryPartner(@PathVariable int deliveryPartnerId) {

		DeliveryPartnerResponseDto deliveryPartnerResponseDto = deliveryPartnerService
				.getDeliveryPartner(deliveryPartnerId);

		return new ResponseEntity(deliveryPartnerResponseDto, HttpStatus.OK);

	}

	@GetMapping("getAllDeliveryPartners")
	ResponseEntity getAllDeliveryPartners() {

		List<DeliveryPartnerResponseDto> list = deliveryPartnerService.getAllDeliveryPartner();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PutMapping("updateDeliveryPartner/{deliveryPartnerId}")
	ResponseEntity updateDeliveryPartner(
			@PathVariable int deliveryPartnerId,
			@RequestBody DeliveryPartnerRequestDto deliveryPartnerRequestDto) {

		DeliveryPartnerResponseDto deliveryPartnerResponseDto = deliveryPartnerService
				.updateDeliveryPartner(deliveryPartnerId, deliveryPartnerRequestDto);

		return new ResponseEntity(deliveryPartnerResponseDto, HttpStatus.OK);
	}
	
	@DeleteMapping("deleteDeliveryPartner/{deliveryPartnerId}")
    ResponseEntity deleteDeliveryPartner(@PathVariable int deliveryPartnerId) {

        deliveryPartnerService.deleteDeliveryPartner(deliveryPartnerId);

        return new ResponseEntity(
                "Delivery partner deleted successfully",
                HttpStatus.OK);
    }
}
