package com.example.testowanieoprogramowania.repositories;

import com.example.testowanieoprogramowania.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> getProductById(Long productId);
}
