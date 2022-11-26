package com.example.softwaretesting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/order")
public class AdminOrderController {
	@GetMapping
	public void getOrder() {

	}

	@PutMapping
	public void sendOrder() {

	}
}
