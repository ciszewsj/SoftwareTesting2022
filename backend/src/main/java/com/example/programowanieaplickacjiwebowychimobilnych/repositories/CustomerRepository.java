package com.example.programowanieaplickacjiwebowychimobilnych.repositories;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByName(String name);
	Optional<Customer> findByEmail(String email);
}
