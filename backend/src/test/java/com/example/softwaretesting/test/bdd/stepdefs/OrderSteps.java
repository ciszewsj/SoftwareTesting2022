package com.example.softwaretesting.test.bdd.stepdefs;

import com.example.softwaretesting.data.entity.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java8.En;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@Slf4j
public class OrderSteps extends AbstractSteps implements En {
	public OrderSteps() {
		Given("admin load orders", () -> {
					executeGet("/admin/order");
				}
		);
		And("admin find order of player {string}", (String expectedString) -> {
			List<?> list = super.testContext().getResponse().body().as(List.class);
			ObjectMapper objectMapper = new ObjectMapper();
			Cart order = null;
			for (Object i : list) {
				Cart b = objectMapper.convertValue(i, Cart.class);
				if (b.getUser().getUsername().equals(expectedString)) {
					order = b;
					break;
				}
			}
			if (order == null) {
				fail();
			}
			super.testContext().set("order", order);
			super.testContext().set("orderId", order.getId());
		});

		Then("admin sent order", () -> {
			long id = super.testContext().get("orderId", Long.class);
			executePut("/admin/order/" + id);
		});

		And("admin load order data", () -> {
			long id = super.testContext().get("orderId", Long.class);
			executeGet("/admin/order/" + id);

			Cart order = super.testContext().getResponse().getBody().as(Cart.class);

			super.testContext().set("order", order);
			super.testContext().set("orderId", order.getId());
		});

		And("admin valid status is {string}", (String expectedString) -> {
			Cart order = super.testContext().get("order", Cart.class);
			assertEquals(expectedString, order.getStatus().toString());
		});
	}
}
