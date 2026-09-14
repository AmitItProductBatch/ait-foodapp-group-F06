package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.CustomerAddressDto;
import com.ait.app.dto.CustomerAddressResponseDto;
import com.ait.app.model.CustomerAddress;

public interface CustomerAddressService {
	CustomerAddressDto addCustomerAddress(int customerId, CustomerAddressDto request);

	CustomerAddressDto updateCustomerAddress(int customerId, CustomerAddressDto customerAddressDto);

	List<CustomerAddress> getCustomerAddressList(int customerId);
}
