package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.CustomerProfileResponseDto;
import com.ait.app.model.Customer;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();
    
    CustomerProfileResponseDto  getCustomer(int id);
}