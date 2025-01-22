package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.CustomerOrder;


public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
