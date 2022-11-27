package com.example.softwaretesting.controller;

import com.example.softwaretesting.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {
	private final ProductUseCase useCase;

	@GetMapping
	public void getProducts() {
		useCase.getItems();
	}

	@GetMapping("/{id}")
	public void getProduct(@PathVariable("id") Long id) {
		useCase.getItem(id);
	}
}
