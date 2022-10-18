package com.example.metodywytwarzaniaoprogramowania.controller;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.usecases.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
	private final ProductUseCase productUseCase;

	@GetMapping
	public List<Product> getAllProducts() {
		log.info("getAllProducts");
		return productUseCase.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable Long productId) {
		log.info("productId: {}", productId);
		return productUseCase.getProduct(productId);
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		log.info("createProduct: {}", product);
		return productUseCase.createProduct(product);
	}

	@PutMapping
	public void updateProduct(@RequestBody Product product) {
		log.info("updateProduct: {}", product);
		productUseCase.updateProduct(product);
	}

	@DeleteMapping("/{productId}")
	public void deleteProduct(@PathVariable Long productId) {
		log.info("deleteProduct: {}", productId);
		productUseCase.deleteProduct(productId);
	}
}
