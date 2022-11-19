package com.example.programowanieaplickacjiwebowychimobilnych.repositories;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
