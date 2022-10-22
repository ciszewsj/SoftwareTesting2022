package com.example.testowanieoprogramowania.repositories;

import com.example.testowanieoprogramowania.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> getUserById(Long playerId);

	Optional<User> getUserByEmailIsLike(String email);
}
