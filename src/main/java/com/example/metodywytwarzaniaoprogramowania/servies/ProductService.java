package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.exception.ShopErrorTypes;
import com.example.metodywytwarzaniaoprogramowania.exception.ShopException;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.usecases.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

	private final ProductRepository productRepository;

	@Override
	public Product getProduct(Long productId) {
		return productRepository.getProductById(productId).orElseThrow(new ShopException(ShopErrorTypes.PRODUCT_NOT_FOUND));

	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		if (product.getId() != null || product.getPrice() <= 0) {
			throw new ShopException(ShopErrorTypes.ILLEGAL_REQUEST_BODY);
		}
		return productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		if (product.getPrice() == null || product.getPrice() <= 0) {
			throw new ShopException(ShopErrorTypes.ILLEGAL_REQUEST_BODY);
		}
		if (productRepository.getProductById(product.getId()).isPresent()) {
			productRepository.save(product);
		} else {
			throw new ShopException(ShopErrorTypes.PRODUCT_NOT_FOUND);
		}
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = productRepository.getProductById(productId).orElseThrow(new ShopException(ShopErrorTypes.PRODUCT_NOT_FOUND));
		productRepository.delete(product);
	}
}
