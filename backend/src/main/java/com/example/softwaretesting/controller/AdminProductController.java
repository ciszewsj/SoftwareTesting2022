package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.CreateProductRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.usecase.AdminProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/admin/product")
public class AdminProductController {
	private final AdminProductUseCase useCase;

	@PostMapping
	public Long addProduct(@RequestBody @Valid CreateProductRequest request) {
		if (request.getPrice() < 0) {
			throw new ParametrizedException(HttpStatus.BAD_REQUEST, ParametrizedException.Status.WRONG_DATA);
		}
		return useCase.create(request);
	}

	@PutMapping("/{id}")
	public void setProductAvailable(@PathVariable("id") Long id) {
		useCase.setAvailable(id);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		useCase.deleteProduct(id);
	}

	@GetMapping
	public List<Item> getProducts() {
		return useCase.getAllProducts();
	}

	@GetMapping("/{id}")
	public Item getProduct(@PathVariable("id") Long id) {
		return useCase.getProduct(id);
	}
}
