package com.example.metodywytwarzaniaoprogramowania.repositories;

import com.example.metodywytwarzaniaoprogramowania.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> getUserById(Long playerId);

	Optional<User> getUserByEmail(String email);
}
