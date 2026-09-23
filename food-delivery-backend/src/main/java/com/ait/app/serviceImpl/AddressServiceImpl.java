package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.AddressRequestDto;
import com.ait.app.dto.AddressResponseDto;
import com.ait.app.exception.AddressNotFoundException;
import com.ait.app.exception.CustomerException;
import com.ait.app.exception.UserNotFoundException;
import com.ait.app.model.Address;
import com.ait.app.model.User;
import com.ait.app.repository.AddressRepository;
import com.ait.app.repository.UserRepository;
import com.ait.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AddressResponseDto createAddress(int userId, AddressRequestDto addressRequestDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

		Address address = new Address();

		address.setLabel(addressRequestDto.getLabel());
		address.setStreet(addressRequestDto.getStreet());
		address.setApartment(addressRequestDto.getApartment());
		address.setLandmark(addressRequestDto.getLandmark());
		address.setCity(addressRequestDto.getCity());
		address.setPostalCode(addressRequestDto.getPostalCode());
		address.setDeliveryInstructions(addressRequestDto.getDeliveryInstructions());

		address.setUser(user);

		Address savedAddress = addressRepository.save(address);

		AddressResponseDto response = new AddressResponseDto();

		response.setLabel(savedAddress.getLabel());
		response.setStreet(savedAddress.getStreet());
		response.setApartment(savedAddress.getApartment());
		response.setLandmark(savedAddress.getLandmark());
		response.setCity(savedAddress.getCity());
		response.setPostalCode(savedAddress.getPostalCode());
		response.setDeliveryInstructions(savedAddress.getDeliveryInstructions());
		response.setUserId(userId);

		return response;
	}

	@Override
	public AddressResponseDto getAddress(int userId, int addressId) {
		userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

		Address address = addressRepository.findByIdAndUserId(addressId, userId)
				.orElseThrow(() -> new AddressNotFoundException("Address not found"));

		AddressResponseDto response = new AddressResponseDto();

		response.setLabel(address.getLabel());
		response.setStreet(address.getStreet());
		response.setApartment(address.getApartment());
		response.setLandmark(address.getLandmark());
		response.setCity(address.getCity());
		response.setPostalCode(address.getPostalCode());
		response.setDeliveryInstructions(address.getDeliveryInstructions());
		response.setUserId(userId);

		return response;

	}

	@Override
	public List<AddressResponseDto> getAddressesOfUser(int userId) {
		List<Address> list = addressRepository.findAllAddressOfUser(userId);
		List<AddressResponseDto> responseList = new ArrayList<>();

		for (Address address : list) {
			AddressResponseDto addressResponseDto = new AddressResponseDto();
			addressResponseDto.setApartment(address.getApartment());
			addressResponseDto.setCity(address.getCity());
			addressResponseDto.setDeliveryInstructions(address.getDeliveryInstructions());
			addressResponseDto.setLabel(address.getLabel());
			addressResponseDto.setLandmark(address.getLandmark());
			addressResponseDto.setPostalCode(address.getPostalCode());
			addressResponseDto.setStreet(address.getStreet());
			addressResponseDto.setUserId(address.getUser().getId());
			responseList.add(addressResponseDto);

		}

		return responseList;
	}

	@Override
	public Address updateAddress(int userId, int addressId, Address address) {
			
			
	        if (!userRepository.existsById(userId)) {
	            throw new CustomerException(
	                    "User not found",
	                    HttpStatus.NOT_FOUND
	            );
	        }

	        Optional<Address> optionalAddress =
	                addressRepository.findByIdAndUserId(addressId, userId);

	        if (optionalAddress.isEmpty()) {
	            throw new CustomerException("Address not found", 
	            		HttpStatus.NOT_FOUND);
	        }
	        Address existingAddress = optionalAddress.get();

	        
	        existingAddress.setLabel(address.getLabel());
	        existingAddress.setStreet(address.getStreet());
	        existingAddress.setApartment(address.getApartment());
	        existingAddress.setLandmark(address.getLandmark());
	        existingAddress.setCity(address.getCity());
	        existingAddress.setPostalCode(address.getPostalCode());
	        
	        
	        existingAddress.setDeliveryInstructions
	        (address.getDeliveryInstructions());

	        
	        return addressRepository.save(existingAddress);
	}

	@Override
		public void deleteAddress(int userId, int addressId) {
			if (!userRepository.existsById(userId)) {
				throw new CustomerException("User Not Found",
						HttpStatus.NOT_FOUND);
			}
			
			Optional<Address> optionalAddress = addressRepository.
					findByIdAndUserId(addressId, userId);
			
			if(optionalAddress.isEmpty()){
				throw new CustomerException("Address Not Found", 
						HttpStatus.BAD_REQUEST);
			}
			
			Address address = optionalAddress.get();
			addressRepository.delete(address);
		
	}

	
}
