package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.usecase.AdminOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin

@RequestMapping("/admin/order")
public class AdminOrderController {
	private final AdminOrderUseCase useCase;

	@GetMapping
	public List<Cart> getOrders() {
		return useCase.getAllOrders();
	}

	@GetMapping("/{id}")
	public Cart getOrder(@PathVariable("id") Long id) {
		return useCase.getOrder(id);
	}

	@PutMapping("/{id}")
	public void sendOrder(@PathVariable("id") Long id) {
		useCase.sendOrder(id);
	}
}
