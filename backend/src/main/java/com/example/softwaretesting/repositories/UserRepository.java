package com.example.softwaretesting.repositories;

import com.example.softwaretesting.data.entity.Role;
import com.example.softwaretesting.data.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ServiceUser, Long> {
	Optional<ServiceUser> findByName(String name);

	@Query("select s.roles from ServiceUser s where s.name=:name")
	List<Role> findUserRoles(@Param("name") String name);
}
