package com.example.softwaretesting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/cart")
public class UserCartRepository {
	@PutMapping("/pay")
	public void payForCart() {

	}

	@GetMapping
	public void getCartItem() {

	}

	@PutMapping
	public void putItemToCart() {

	}

	@DeleteMapping
	public void deleteItemFromCart() {

	}
}
