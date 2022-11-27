package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.usecase.UserOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/order")
public class UserOrderController {
	private final UserOrderUseCase useCase;

	@GetMapping

	public void getOrders(@AuthenticationPrincipal ServiceUser user) {
		useCase.getOrders(user);
	}

	@GetMapping("/{id}")
	public void getOrder(@AuthenticationPrincipal ServiceUser user, @PathVariable("id") Long id) {
		useCase.getOrder(user, id);
	}
}
