package com.example.softwaretesting.test.microbenchmark;

import com.example.softwaretesting.controller.AdminProductController;
import com.example.softwaretesting.controller.AuthenticationController;
import com.example.softwaretesting.data.request.CreateProductRequest;
import com.example.softwaretesting.data.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class ApplicationJMHTests {
	private static AdminProductController adminProductController;

	@Autowired
	public void setController(AuthenticationController authenticationController,
	                          AdminProductController adminProductController) {
		ApplicationJMHTests.adminProductController = adminProductController;
	}

	@Test
	public void runBenchmarks() throws Exception {
		Options opts = new OptionsBuilder()
				.include("\\." + this.getClass().getSimpleName() + "\\.")
				.warmupIterations(0)
				.measurementIterations(3)
				.forks(0)
				.threads(1)
				.shouldDoGC(true)
				.shouldFailOnError(true)
				.jvmArgs("-server")
				.build();

		new Runner(opts).run();
	}

	@Benchmark
	public void addProduct() {
		adminProductController.addProduct(CreateProductRequest
				.builder()
				.name("Kayak")
				.available(true)
				.price(1000L)
				.build());
	}

	@Benchmark
	public void setProductAvailable(ProductToAvailable product) {
		adminProductController.setProductAvailable(product.id);
	}

	@Benchmark
	public void deleteProduct(ProductToDelete product) {
		adminProductController.deleteProduct(product.id);
	}

	@Benchmark
	public void getProducts() {
		adminProductController.getProducts();
	}

	@Benchmark
	public void getProduct(ProductToDelete product) {
		adminProductController.getProduct(product.id);
	}


	@State(Scope.Benchmark)
	public static class ProductToDelete {
		public long id;

		@Setup(Level.Invocation)
		public void prepare() {
			id = adminProductController.addProduct(CreateProductRequest.builder()
					.name("Kayak")
					.available(true)
					.price(1000L)
					.build());
		}
	}

	@State(Scope.Benchmark)
	public static class ProductToAvailable {
		public long id;

		@Setup(Level.Invocation)
		public void prepare() {
			id = adminProductController.addProduct(CreateProductRequest.builder()
					.name("Kayak")
					.available(false)
					.price(1000L)
					.build());
		}
	}
}
