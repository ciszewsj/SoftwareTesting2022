package com.example.softwaretesting.test.microbenchmark;

import com.example.softwaretesting.controller.AdminProductController;
import com.example.softwaretesting.data.request.CreateProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class ApplicationProfilerTests {
	private static AdminProductController adminProductController;
	private static long productToDelete;
	private static long productToAvailable;
	private static long productToGet;

	@Autowired
	public void setController(AdminProductController adminProductController) {
		ApplicationProfilerTests.adminProductController = adminProductController;
	}

	@BeforeClass
	public static void init() {
		productToDelete = adminProductController.addProduct(CreateProductRequest.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
		productToAvailable = adminProductController.addProduct(CreateProductRequest.builder()
				.name("Kayak")
				.available(false)
				.price(1000L)
				.build());
		productToGet = adminProductController.addProduct(CreateProductRequest.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
	}

	@Test
	public void addProduct() {
		adminProductController.addProduct(CreateProductRequest
				.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
	}

	@Test
	public void setProductAvailable() {
		adminProductController.setProductAvailable(productToAvailable);
	}

	@Test
	public void deleteProduct() {
		adminProductController.deleteProduct(productToDelete);
	}

	@Test
	public void getProducts() {
		adminProductController.getProducts();
	}

	@Test
	public void getProduct() {
		adminProductController.getProduct(productToGet);
	}
}
