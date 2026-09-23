package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.AddressRequestDto;
import com.ait.app.dto.AddressResponseDto;
import com.ait.app.model.Address;

public interface AddressService {

	AddressResponseDto createAddress(int userId, AddressRequestDto addressRequestDto);

	AddressResponseDto getAddress(int userId, int addressId);

	List<AddressResponseDto> getAddressesOfUser(int userId);
	
	 Address updateAddress (int userId, int addressId, Address address);
	
	void deleteAddress(int userId, int addressId);
}
