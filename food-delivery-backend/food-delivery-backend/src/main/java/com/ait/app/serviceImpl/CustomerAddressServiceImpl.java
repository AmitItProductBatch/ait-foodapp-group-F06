package com.ait.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.CustomerAddressDto;
import com.ait.app.dto.CustomerAddressResponseDto;
import com.ait.app.exception.CustomerException;
import com.ait.app.model.Customer;
import com.ait.app.model.CustomerAddress;
import com.ait.app.repository.CustomerAddressRepository;
import com.ait.app.repository.CustomerRepository;
import com.ait.app.service.CustomerAddressService;
import com.ait.app.service.CustomerService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerAddressDto addCustomerAddress(int customerId, CustomerAddressDto customerAddressDto) {
		CustomerAddress ad = new CustomerAddress();

		ad.setCity(customerAddressDto.getCity());
		ad.setAddressLine(customerAddressDto.getAddressLine());
		ad.setPincode(customerAddressDto.getPincode());
		ad.setState(customerAddressDto.getState());

		CustomerAddress savedAddress = customerAddressRepository.save(ad);
		CustomerAddressResponseDto response = new CustomerAddressResponseDto();
		response.setAddressLine(savedAddress.getAddressLine());
		response.setPincode(savedAddress.getPincode());
		response.setState(savedAddress.getState());
		response.setCity(savedAddress.getCity());
		response.setCustomerAddressId(savedAddress.getId());
		return customerAddressDto;
	}

	@Override
	public List<CustomerAddress> getCustomerAddressList(int customerId) {
		List<CustomerAddress> list = customerAddressRepository.findAll();
		return list;
	}

	public CustomerAddressDto updateCustomerAddress(int customerId, CustomerAddressDto customerAddressDto) {

		Optional optional = customerAddressRepository.findById(customerId);
		if (optional.isEmpty()) {
			throw new CustomerException("address not found", HttpStatus.NOT_FOUND);
		}
		CustomerAddress customerAddress = (CustomerAddress) optional.get();

		customerAddress.setAddressLine(customerAddressDto.getAddressLine());
		customerAddress.setCity(customerAddressDto.getCity());
		customerAddress.setPincode(customerAddressDto.getPincode());
		customerAddress.setState(customerAddressDto.getState());

		CustomerAddress updatedAddress = customerAddressRepository.save(customerAddress);
		CustomerAddressDto response = new CustomerAddressDto();
		response.setAddressLine(updatedAddress.getAddressLine());
		response.setCity(updatedAddress.getCity());
		response.setPincode(updatedAddress.getPincode());
		response.setState(updatedAddress.getState());
		return customerAddressDto;

	}

}
