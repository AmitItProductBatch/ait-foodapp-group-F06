package com.ait.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CustomerDto;
import com.ait.app.dto.CustomerProfileResponseDto;
import com.ait.app.dto.UpdateProfileDto;
import com.ait.app.exception.CustomerException;

import com.ait.app.exception.CustomerProfileException;
import com.ait.app.exception.UpdateCustomerProfileException;
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

	@Override
	public CustomerProfileResponseDto getCustomer(int id) {
		Customer customer = customerRepository.findById(id).orElseThrow(
				() -> new UpdateCustomerProfileException("Customer not found with id: " + id, HttpStatus.NOT_FOUND));

		CustomerProfileResponseDto dto = new CustomerProfileResponseDto();

		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		dto.setAddress(customer.getAddress());
		dto.setRole(customer.getRole());

		return dto;
	}

	@Override
	public CustomerDto updateProfile(int id, UpdateProfileDto updateProfileDto) {
		
		 Customer customer = customerRepository.findById(id).get();
		 if(customer==null) {
			 throw new CustomerProfileException("customer not found", HttpStatus.NOT_FOUND);
		 }
		           

		    if (updateProfileDto.getName() != null) {
		        customer.setName(updateProfileDto.getName());
		    }

		    if(updateProfileDto.getMobileNo() !=0) {
		    	customer.setMobileNo(updateProfileDto.getMobileNo());
		    }
		    if (updateProfileDto.getAddress() != null) {
		        customer.setAddress(updateProfileDto.getAddress());
		    }
		    if(updateProfileDto.getEmail() !=null) {
		    	customer.setEmail(updateProfileDto.getEmail());
		    }

		    Customer updatedCustomer = customerRepository.save(customer);
		    CustomerDto customerDto=new CustomerDto();
		    customerDto.setId(updatedCustomer.getId());
		    customerDto.setAddress(updatedCustomer.getAddress());
		    customerDto.setEmail(updatedCustomer.getEmail());
		    customerDto.setMobileNo(updatedCustomer.getMobileNo());
		    customerDto.setName(updatedCustomer.getName());
		    customerDto.setRole(updatedCustomer.getRole());
		    return customerDto;
		    

	}

	@Override
	public void deleteUserByID(int id) {

        if (!customerRepository.existsById(id)) {
            throw new CustomerException("customer not found", HttpStatus.NOT_FOUND);
        }

        customerRepository.deleteById(id);

		
	}
	
}
