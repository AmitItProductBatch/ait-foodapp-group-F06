package com.ait.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.CustomerAddress;

public interface CustomerAddressRepository
extends JpaRepository<CustomerAddress, Integer> {
    public List<CustomerAddress> findByCustomerId(int var1);
}