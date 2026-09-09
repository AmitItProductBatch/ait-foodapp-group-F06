package com.ait.app.service;

import java.util.List;

import com.ait.app.dto.AddressResponseDto;
import com.ait.app.dto.AdressDto;
import com.ait.app.model.Adress;
import com.ait.app.model.Customer;

public interface AddressService {

    AddressResponseDto createAddress(int customerId, AdressDto request);
    
    AddressResponseDto updateAddress(int addressId, AdressDto request);

    List<Adress> getAdress(int customerId);
    
}
