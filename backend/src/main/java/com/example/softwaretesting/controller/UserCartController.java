package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.DeleteItemFromCartRequest;
import com.example.softwaretesting.data.request.PutItemToCartRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.usecase.UserCartUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/cart")
public class UserCartController {
	private final UserCartUseCase useCase;

	@PutMapping("/pay")
	public void payForCart(@AuthenticationPrincipal ServiceUser user) {
		useCase.payForCart(user);
	}

	@GetMapping
	public Cart getCart(@AuthenticationPrincipal ServiceUser user) {
		return useCase.getCart(user);
	}

	@PutMapping
	public void putItemToCart(@AuthenticationPrincipal ServiceUser user,
	                          @RequestBody @Valid PutItemToCartRequest request) {
		if (request.getNumberOfItems() < 0) {
			throw new ParametrizedException(HttpStatus.BAD_REQUEST, ParametrizedException.Status.WRONG_DATA);
		}
		useCase.putItemToCart(user, request);
	}

	@DeleteMapping
	public void deleteItemFromCart(@AuthenticationPrincipal ServiceUser user,
	                               @RequestBody @Valid DeleteItemFromCartRequest request) {
		if (request.getNumberOfItems() < 0) {
			throw new ParametrizedException(HttpStatus.BAD_REQUEST, ParametrizedException.Status.WRONG_DATA);
		}
		useCase.deleteItemFromCart(user, request);
	}
}
