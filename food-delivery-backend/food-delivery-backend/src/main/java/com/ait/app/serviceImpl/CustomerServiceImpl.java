package com.ait.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.exception.CustomerException;
import com.ait.app.model.Customer;
import com.ait.app.repository.CustomerRepository;
import com.ait.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException("email not found", HttpStatus.CONFLICT);
        }

        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> list = customerRepository.findAll();

        return list;
    }
}
