package com.example.metodywytwarzaniaoprogramowania.repositories;

import com.example.metodywytwarzaniaoprogramowania.data.Return;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Return, Long> {

	List<Return> getAllByOrderUserId(Long useId);
}
