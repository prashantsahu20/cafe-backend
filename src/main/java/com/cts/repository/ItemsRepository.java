package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entities.Items;


public interface ItemsRepository extends JpaRepository<Items, Long> {
}

