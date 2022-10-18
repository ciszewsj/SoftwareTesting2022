package com.example.metodywytwarzaniaoprogramowania.usecases;

import com.example.metodywytwarzaniaoprogramowania.data.Product;

import java.util.List;

public interface ProductUseCase {
	Product getProduct(Long productId);

	List<Product> getAllProducts();

	Product createProduct(Product product);

	void updateProduct(Product product);

	void deleteProduct(Long product);


}
