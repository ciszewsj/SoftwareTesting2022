package com.example.softwaretesting.test.microbenchmark;

import com.example.softwaretesting.controller.AdminProductController;
import com.example.softwaretesting.data.request.CreateProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ApplicationProfilerTests {
	private static AdminProductController adminProductController;
	private static long product;
	private static long timeUp;

	@Parameterized.Parameters
	public static Object[][] data() {
		return new Object[10][0];
	}

	@Autowired
	public void setController(AdminProductController adminProductController) {
		ApplicationProfilerTests.adminProductController = adminProductController;
	}


	@BeforeEach
	public void init() {
		product = adminProductController.addProduct(CreateProductRequest.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
		timeUp = System.nanoTime();
	}

	@AfterEach
	public void tearDown() {
		log.info("Time : {} ns", System.nanoTime() - timeUp);
	}


	@RepeatedTest(10)
	public void addProduct() {
		adminProductController.addProduct(CreateProductRequest
				.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
	}

	@RepeatedTest(10)
	public void setProductAvailable() {
		adminProductController.setProductAvailable(product);
	}

	@RepeatedTest(10)
	public void deleteProduct() {
		adminProductController.deleteProduct(product);
	}

	@RepeatedTest(10)
	public void getProducts() {
		adminProductController.getProducts();
	}

	@RepeatedTest(10)
	public void getProduct() {
		adminProductController.getProduct(product);
	}
}
