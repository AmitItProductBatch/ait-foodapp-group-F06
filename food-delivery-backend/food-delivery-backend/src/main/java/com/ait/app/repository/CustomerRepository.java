package com.ait.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.app.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public boolean existsByEmail(String email);

}