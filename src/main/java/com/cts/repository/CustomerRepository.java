package com.cts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String username);
}
