package com.example.metodywytwarzaniaoprogramowania;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.servies.ProductService;
import com.example.metodywytwarzaniaoprogramowania.usecases.ProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	ProductRepository productRepository;
	ProductUseCase productUseCase;

	@BeforeEach
	public void setup() {
		productRepository = mock(ProductRepository.class);
		productUseCase = new ProductService(productRepository);
	}

	@Test
	void testGetProduct() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setAmount(120);
		product.setPrice(12L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		Product productFromServer = productUseCase.getProduct(productId);

		assertEquals(product, productFromServer);
	}

	@Test
	void testGetProductWhenNoProductWithId() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setAmount(120);
		product.setPrice(12L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> productUseCase.getProduct(productId));

	}

	@Test
	void testGetAllProducts() {
		Product productA = new Product();
		productA.setId(1L);
		productA.setAmount(120);
		productA.setPrice(12999L);
		productA.setName("Kayak");
		productA.setDescription("Awesome Kayak");
		Product productB = new Product();
		productB.setId(2L);
		productB.setAmount(21);
		productB.setPrice(1200000L);
		productB.setName("HeliCopter");
		productB.setDescription("Awesome HeliCopter");
		Product productC = new Product();
		productC.setId(3L);
		productC.setAmount(1);
		productC.setPrice(1200000099L);
		productC.setName("WarShip");
		productC.setDescription("Awesome WarShip");
		when(productRepository.findAll()).thenReturn(List.of(productA, productB, productC));

		List<Product> products = productUseCase.getAllProducts();

		assertEquals(3, products.size());
	}

	@Test
	void testCreateProduct() {
		Product product = new Product();
		product.setAmount(120);
		product.setPrice(12999L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");
		Product productToBeReturned = new Product();
		productToBeReturned.setId(1L);
		productToBeReturned.setAmount(120);
		productToBeReturned.setPrice(12999L);
		productToBeReturned.setName("Kayak");
		productToBeReturned.setDescription("Awesome Kayak");
		when(productRepository.save(any(Product.class))).thenReturn(productToBeReturned);

		Product returnProduct = productRepository.save(product);

		assertEquals(productToBeReturned, returnProduct);
	}

	@Test
	void testCreateProductWithId() {
		Product product = new Product();
		product.setId(1L);
		product.setAmount(120);
		product.setPrice(12999L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");

		assertThrows(IllegalArgumentException.class, () -> productUseCase.createProduct(product));
	}

	@Test
	void testUpdateProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setAmount(120);
		product.setPrice(12999L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		productUseCase.updateProduct(product);

		verify(productRepository, times(1)).getProductById(any());
		verify(productRepository, times(1)).save(any());
	}

	@Test
	void testUpdateProductWhenProductNotExist() {
		Product product = new Product();

		assertThrows(IllegalArgumentException.class, () -> productUseCase.updateProduct(product));

		verify(productRepository, times(1)).getProductById(any());
		verify(productRepository, times(0)).save(any());
	}

	@Test
	void testDeleteProduct() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setAmount(120);
		product.setPrice(12999L);
		product.setName("Kayak");
		product.setDescription("Awesome Kayak");
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		productUseCase.deleteProduct(productId);

		verify(productRepository, times(1)).getProductById(any());
		verify(productRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteProductWhenNoProductExist() {
		Long productId = 1L;
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> productUseCase.deleteProduct(productId));

		verify(productRepository, times(1)).getProductById(any());
		verify(productRepository, times(0)).delete(any());
	}
}
