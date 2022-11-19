package com.example.programowanieaplickacjiwebowychimobilnych.repositories;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
