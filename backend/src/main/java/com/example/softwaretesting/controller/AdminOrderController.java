package com.example.softwaretesting.controller;

import com.example.softwaretesting.usecase.AdminOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/order")
public class AdminOrderController {
	private final AdminOrderUseCase useCase;

	@GetMapping
	public void getOrders() {
		useCase.getAllOrders();
	}

	@GetMapping("/{id}")
	public void getOrder(@PathVariable("id") Long id) {
		useCase.getOrder(id);
	}

	@PutMapping("/{id}")
	public void sendOrder(@PathVariable("id") Long id) {
		useCase.sendOrder(id);
	}
}
