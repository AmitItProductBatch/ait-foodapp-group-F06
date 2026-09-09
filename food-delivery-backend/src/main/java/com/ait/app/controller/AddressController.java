package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.AddressResponseDto;
import com.ait.app.dto.AdressDto;
import com.ait.app.model.Adress;
import com.ait.app.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping("createaddress/{customerId}")
	public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AdressDto request, int customer_id) {

		AddressResponseDto address = addressService.createAddress(customer_id, request);

		return new ResponseEntity<>(address, HttpStatus.CREATED);
	}

	@GetMapping("getaddresses/{customerId}")
	public ResponseEntity<List<Adress>> getAddressById(@PathVariable int customerId) {

		return ResponseEntity.ok(addressService.getAdress(customerId));
	}

	@PutMapping("upateaddresses/{customerId}")
	public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable int addressId,
			@RequestBody AdressDto request) {

		return ResponseEntity.ok(addressService.updateAddress(addressId, request));
	}

}
