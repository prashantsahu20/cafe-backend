package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.entities.Customer;


public interface UserRepository extends JpaRepository<Customer,Long> {
        Customer findByEmail(String email);   
}
