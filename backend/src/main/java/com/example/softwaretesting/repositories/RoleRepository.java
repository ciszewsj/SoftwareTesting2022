package com.example.softwaretesting.repositories;

import com.example.softwaretesting.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
