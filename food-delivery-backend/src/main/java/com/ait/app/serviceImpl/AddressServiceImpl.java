package com.ait.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.AddressResponseDto;
import com.ait.app.dto.AdressDto;
import com.ait.app.exception.CustomerException;
import com.ait.app.model.Adress;
import com.ait.app.model.Customer;
import com.ait.app.repository.AdressRepository;
import com.ait.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AdressRepository addressRepository;

	@Override
	public AddressResponseDto createAddress(int customerId, AdressDto request) {

		Adress ad = new Adress();
		ad.setLabel(request.getLabel());
		ad.setApartment(request.getApartment());
		ad.setCity(request.getCity());
		ad.setDeliveryInstructions(request.getDeliveryInstructions());
		ad.setLandmark(request.getLandmark());
		ad.setPostalCode(request.getPostalCode());
		ad.setStreetAddress(request.getStreetAddress());

		Adress savedAddress = addressRepository.save(ad);

		AddressResponseDto response = new AddressResponseDto();
		response.setAddressId(response.getAddressId());
		response.setMessage(response.getMessage());
		return response;
	}

	@Override
	public AddressResponseDto updateAddress(int addressId, AdressDto request) {

		Optional<Adress> optional = addressRepository.findById(addressId);

		if (optional.isEmpty()) {
			throw new CustomerException("Address not found with id : " + addressId, HttpStatus.NOT_FOUND);
		}

		Adress address = optional.get();

		address.setLabel(request.getLabel());
		address.setStreetAddress(request.getStreetAddress());
		address.setApartment(request.getApartment());
		address.setLandmark(request.getLandmark());
		address.setCity(request.getCity());
		address.setPostalCode(request.getPostalCode());
		address.setDeliveryInstructions(request.getDeliveryInstructions());

		Adress updatedAddress = addressRepository.save(address);

		AddressResponseDto response = new AddressResponseDto();
		response.setAddressId(updatedAddress.getId());
		response.setMessage("Address updated successfully");

		return response;
	}
	@Override
	public List<Adress> getAdress(int customerId) {
		List<Adress> list = addressRepository.findAll();
        return list;
	}
}