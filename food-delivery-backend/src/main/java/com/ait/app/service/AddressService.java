package com.ait.app.service;

import com.ait.app.dto.AddressRequestDto;
import com.ait.app.dto.AddressResponseDto;

public interface AddressService {

	AddressResponseDto createAddress(int userId, AddressRequestDto addressRequestDto);

	AddressResponseDto getAddress(int userId, int addressId);

}
