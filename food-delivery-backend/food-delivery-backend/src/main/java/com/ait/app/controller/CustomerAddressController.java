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

import com.ait.app.dto.CustomerAddressDto;
import com.ait.app.dto.CustomerAddressResponseDto;
import com.ait.app.model.CustomerAddress;
import com.ait.app.service.CustomerAddressService;
@RestController
public class CustomerAddressController {
	@Autowired
	CustomerAddressService customerAddressService;

	@PostMapping("createAddress/{customerId}")
	public ResponseEntity createAddress(@RequestBody CustomerAddressDto request, @PathVariable int customerId) {
		CustomerAddressDto customerAddressDto = customerAddressService.addCustomerAddress(customerId, request);
		return new ResponseEntity(customerAddressDto, HttpStatus.CREATED);

	}

	@GetMapping("getAddress/{customerId}")
	public ResponseEntity<List<CustomerAddress>> getAddressById(@PathVariable int customerId) {
		List<CustomerAddress> addressList = customerAddressService.getCustomerAddressList(customerId);
		return new ResponseEntity<>(addressList, HttpStatus.OK);

	}

	@PutMapping("upateAddress/{addressId}")
	public ResponseEntity updateAddress(@PathVariable int addressId, @RequestBody CustomerAddressDto request) {
		CustomerAddressDto customerAddressDto = customerAddressService.updateCustomerAddress(addressId, request);
		return new ResponseEntity(customerAddressDto, HttpStatus.OK);

	}
}
