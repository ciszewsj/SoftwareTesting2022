package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.CreateProductRequest;
import com.example.softwaretesting.data.request.DeleteProductRequest;
import com.example.softwaretesting.data.request.SetProductAvailableRequest;
import com.example.softwaretesting.usecase.AdminProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/product")
public class AdminProductController {
	AdminProductUseCase useCase;

	@PostMapping
	public void addProduct(@RequestBody @Valid CreateProductRequest request) {
		useCase.create(request);
	}

	@PutMapping
	public void setProductAvailable(@RequestBody SetProductAvailableRequest request) {
		useCase.setAvailable(request);
	}

	@DeleteMapping
	public void deleteProduct(@RequestBody DeleteProductRequest request) {
		useCase.deleteProduct(request);
	}

	@GetMapping
	public List<Item> getProducts() {
		return useCase.getAllProducts();
	}
}
