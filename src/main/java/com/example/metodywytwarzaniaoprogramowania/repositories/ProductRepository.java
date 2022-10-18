package com.example.metodywytwarzaniaoprogramowania.repositories;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> getProductById(Long productId);
}
