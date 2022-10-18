package com.example.metodywytwarzaniaoprogramowania.controller;

import com.example.metodywytwarzaniaoprogramowania.data.requests.ChangeOrderStatusRequest;
import com.example.metodywytwarzaniaoprogramowania.data.requests.LongProductIdRequestBody;
import com.example.metodywytwarzaniaoprogramowania.data.Order;
import com.example.metodywytwarzaniaoprogramowania.usecases.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
	private final OrderUseCase orderUseCase;

	@GetMapping
	public List<Order> getOrders() {
		return orderUseCase.getAllOrders();
	}

	@PutMapping("/{userId}")
	public void addProductToOrder(@PathVariable Long userId, @RequestBody LongProductIdRequestBody productIdRequestBody) {
		log.info("addProductToOrder: {} , {}", userId, productIdRequestBody.getProductId());
		orderUseCase.addProductToOrder(productIdRequestBody.getProductId(), userId);
	}

	@DeleteMapping("/{orderId}")
	public void deleteProductFromOrder(@PathVariable Long orderId, @RequestBody LongProductIdRequestBody productIdRequestBody) {
		log.info("deleteProductFromOrder: {} , {}", orderId, productIdRequestBody.getProductId());
		orderUseCase.deleteProductFromOrder(productIdRequestBody.getProductId(), orderId);
	}

	@GetMapping("/getShoppingCart/{userId}")
	public Order getShoppingCart(@PathVariable Long userId) {
		log.info("getShoppingCart: {}", userId);
		return orderUseCase.getShoppingCart(userId);
	}

	@PutMapping("/pay/{orderId}")
	public void payForOrder(@PathVariable Long orderId) {
		log.info("payForOrder: {}", orderId);
		orderUseCase.payForOrder(orderId);
	}

	@PutMapping("/changeStatus/{orderId}")
	public void changeOrderStatus(@PathVariable Long orderId, @RequestBody ChangeOrderStatusRequest orderStatusRequest) {
		log.info("changeOrderStatus: {} , {}", orderId, orderStatusRequest.getStatus());
		orderUseCase.changeOrderStatus(orderId, orderStatusRequest.getStatus());
	}

	@GetMapping("/price/{orderId}")
	public Long getOrderPrice(@PathVariable Long orderId) {
		log.info("getOrderPrice: {}", orderId);
		return orderUseCase.getOrderPrice(orderId);
	}


}
