package com.cts.service;

import java.util.List;
import java.util.Optional;

import com.cts.entities.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getByEmail(String email);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
    Optional<Customer> verifyPassword(String email, String rawPassword);
}

