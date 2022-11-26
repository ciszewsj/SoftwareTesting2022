package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.*;
import com.example.softwaretesting.data.response.LoginResponse;

import java.util.List;

public interface AdminProductUseCase {
	void create(CreateProductRequest request);

	void setAvailable(SetProductAvailableRequest request);

	void deleteProduct(DeleteProductRequest request);

	List<Item> getAllProducts();
}
