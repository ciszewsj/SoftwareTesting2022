package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.CreateProductRequest;

import java.util.List;

public interface AdminProductUseCase {
	Long create(CreateProductRequest request);

	void setAvailable(Long id);

	void deleteProduct(Long id);

	List<Item> getAllProducts();

	Item getProduct(Long id);
}
