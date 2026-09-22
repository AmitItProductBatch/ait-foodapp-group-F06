package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.AddressRequestDto;
import com.ait.app.dto.AddressResponseDto;
import com.ait.app.service.AddressService;

@RestController
@RequestMapping("/api/user")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/{userId}")
	public ResponseEntity<AddressResponseDto> createAddress(@PathVariable int userId,
			@RequestBody AddressRequestDto addressRequestDto) {

		AddressResponseDto response = addressService.createAddress(userId, addressRequestDto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}/getAddress/{addressId}")
	public ResponseEntity<AddressResponseDto> getAddress(@PathVariable int userId, @PathVariable int addressId) {

		AddressResponseDto response = addressService.getAddress(userId, addressId);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("getAllAddresses/{userId}")
	ResponseEntity getAddressesOfUser(@PathVariable int userId) {

		List<AddressResponseDto> l = addressService.getAddressesOfUser(userId);
		return new ResponseEntity(l, HttpStatus.OK);
	}
}
