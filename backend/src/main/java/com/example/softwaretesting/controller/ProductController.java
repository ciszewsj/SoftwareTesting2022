package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	private final ProductUseCase useCase;

	@GetMapping
	public List<Item> getProducts() {
		log.info("GET PRODUCT");
		return useCase.getItems();
	}

	@GetMapping("/{id}")
	public Item getProduct(@PathVariable("id") Long id) {
		return useCase.getItem(id);
	}
}
