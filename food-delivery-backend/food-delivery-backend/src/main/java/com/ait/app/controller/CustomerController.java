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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.CustomerProfileResponseDto;
import com.ait.app.dto.CustomerResponse;
import com.ait.app.dto.UpdateCustomerRequest;
import com.ait.app.model.Customer;
import com.ait.app.repository.CustomerRepository;
import com.ait.app.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("addCustomer")
	ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

		Customer savedCustomer = customerService.addCustomer(customer);

		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

	@GetMapping("getAllCustomers")
	ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> list = customerService.getAllCustomers();

		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerProfileResponseDto> getCustomer(@PathVariable int id) {
		CustomerProfileResponseDto responce = customerService.getCustomer(id);

		return new ResponseEntity<CustomerProfileResponseDto>(responce, HttpStatus.OK);
	}

	// Update
	@PutMapping("update/{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable int id,
			@RequestBody UpdateCustomerRequest request) {

		CustomerResponse updateCustomer = customerService.UpdateCustomer(id, request);

		return new ResponseEntity<CustomerResponse>(updateCustomer, HttpStatus.OK);

	}
}
