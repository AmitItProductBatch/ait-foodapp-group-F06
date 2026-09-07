package com.ait.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.model.Customer;
import com.ait.app.repository.CustomerRepository;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("addCustomer")
    ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("getAllCustomers")
    ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> list = customerRepository.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

