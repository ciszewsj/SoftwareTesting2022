package com.example.softwaretesting.test.bdd.stepdefs;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.request.DeleteItemFromCartRequest;
import com.example.softwaretesting.data.request.PutItemToCartRequest;
import cucumber.api.java.en.When;
import cucumber.api.java8.An;
import cucumber.api.java8.En;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class CartSteps extends AbstractSteps implements En {
	public CartSteps() {
		Given("user want to add {string} products to cart", (String expectedString) -> {
			int id = super.testContext().get("productId", Integer.class);
			int numberOfProducts = Integer.parseInt(expectedString);
			PutItemToCartRequest request = new PutItemToCartRequest();
			request.setProductId((long) id);
			request.setNumberOfItems(numberOfProducts);
			super.testContext().setPayload(request);
		});
		When("user add product to cart", () -> {
			executePut("/user/cart");
		});
		And("user load cart info", () -> {
			executeGet("/user/cart");
		});
		Then("user check if product added correctly and number is {string}", (String expectedString) -> {
			int numberOfProducts = Integer.parseInt(expectedString);
			PutItemToCartRequest request = super.testContext().getPayload(PutItemToCartRequest.class);
			Cart cart = super.testContext().getResponse().body().as(Cart.class);
			if (numberOfProducts == 0) {
				assertFalse(cart.getItems().stream().anyMatch(i -> i.getItem().getId().equals(request.getProductId())));
			} else {
				assertTrue(cart.getItems().stream().anyMatch(i -> i.getItem().getId().equals(request.getProductId()) && i.getNumberOfItems() == numberOfProducts));
			}
		});

		Given("user want to remove {string} products from cart", (String expectedString) -> {
			int id = super.testContext().get("productId", Integer.class);
			int numberOfProducts = Integer.parseInt(expectedString);
			DeleteItemFromCartRequest request = new DeleteItemFromCartRequest();
			request.setProductId((long) id);
			request.setNumberOfItems(numberOfProducts);
			log.error("REQUSTE: {}", request);
			super.testContext().setPayload(request);
		});
		When("user remove product from cart", () -> {
			executeDelete("/user/cart");
		});
		Then("user check if product removed correctly and number is {string}", (String expectedString) -> {
			int numberOfProducts = Integer.parseInt(expectedString);
			DeleteItemFromCartRequest request = super.testContext().getPayload(DeleteItemFromCartRequest.class);
			Cart cart = super.testContext().getResponse().body().as(Cart.class);
			if (numberOfProducts == 0) {
				assertFalse(cart.getItems().stream().anyMatch(i -> i.getItem().getId().equals(request.getProductId())));
			} else {
				assertTrue(cart.getItems().stream().anyMatch(i -> i.getItem().getId().equals(request.getProductId()) && i.getNumberOfItems() == numberOfProducts));
			}
		});

		When("user pay for cart", () -> {
			executePut("/user/cart/pay");
		});
	}
}
