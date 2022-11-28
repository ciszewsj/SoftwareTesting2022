package com.example.softwaretesting.test.bdd.stepdefs;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.CreateProductRequest;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
public class CreateProductSteps extends AbstractSteps implements En {
	public CreateProductSteps() {
		Given("admin wants to create new product using data", (DataTable productData) -> {
			List<CreateProductRequest> createProductRequests = productData.asList(CreateProductRequest.class);
			super.testContext()
					.setPayload(createProductRequests.get(0));
		});

		When("admin creates new product", () -> {
			String createEmployeeUrl = "/admin/product";
			executePost(createEmployeeUrl);
			if (super.testContext().getResponse().getStatusCode() == 200) {
				super.testContext().set("productId", super.testContext().getResponse().getBody().as(Integer.class));
			}
		});

		Then("admin get product data", () -> {
			int id = super.testContext().get("productId", Integer.class);
			super.executeGet("/admin/product/" + id);
		});

		Then("admin check added product data", () -> {
			CreateProductRequest createProductRequest = super.testContext().getPayload(CreateProductRequest.class);
			Item item = super.testContext().getResponse().as(Item.class);

			assertNotNull(item.getId());
			if (createProductRequest.getName() != null) {
				assertEquals(createProductRequest.getName(), item.getName());
			} else {
				fail("null");
			}
			if (createProductRequest.getPrice() != null) {
				assertEquals(createProductRequest.getPrice(), item.getPrice());
			} else {
				fail("null");
			}
			assertEquals(createProductRequest.getAvailable() ? Item.Status.AVAILABLE : Item.Status.NOT_AVAILABLE, item.getStatus());
		});

		When("admin change product status to {string}", (String expectedStatus) -> {
			int productId = super.testContext().get("productId", Integer.class);
			switch (expectedStatus) {
				case "AVAILABLE":
					executePut("/admin/product/" + productId);
					break;
				case "NOT_AVAILABLE":
					executeDelete("/admin/product/" + productId);
					break;
				default:
					fail("Unexpected error");
			}
		});

		And("admin check if product is {string}", (String expectedStatus) -> {
			Item product = super.testContext().getResponse().getBody().as(Item.class);
			log.error("Product status : {}", product.getStatus().toString());
			assertEquals(expectedStatus, product.getStatus().toString());
		});
	}
}
