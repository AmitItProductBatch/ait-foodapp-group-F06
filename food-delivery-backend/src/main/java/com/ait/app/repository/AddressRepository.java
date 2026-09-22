package com.ait.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ait.app.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByIdAndUserId(int addressId, int userId);

	@Query("SELECT a FROM Address a WHERE a.user.id = :userId")
	List<Address> findAllAddressOfUser(@Param("userId") int userId);
}
