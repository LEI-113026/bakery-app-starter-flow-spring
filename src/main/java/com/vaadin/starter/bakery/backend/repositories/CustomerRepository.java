package com.vaadin.starter.bakery.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.starter.bakery.backend.data.entity.Customer;

/**
 * Repository interface for managing Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
