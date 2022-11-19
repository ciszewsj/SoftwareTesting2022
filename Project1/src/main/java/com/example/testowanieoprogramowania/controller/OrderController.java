package com.example.testowanieoprogramowania.controller;

import com.example.testowanieoprogramowania.data.Order;
import com.example.testowanieoprogramowania.data.requests.ChangeOrderStatusRequest;
import com.example.testowanieoprogramowania.data.requests.LongProductIdRequestBody;
import com.example.testowanieoprogramowania.usecases.OrderUseCase;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
class A {
	Long id;
	String name;
}

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class OrderController {
	private final OrderUseCase orderUseCase;

	@GetMapping("/test")
	public List<A> getTest() {
		List<A> list = new ArrayList<>();
		list.add(A.builder().id(1L).name("Andrzej").build());
		list.add(A.builder().id(2L).name("Paweł").build());
		list.add(A.builder().id(3L).name("Janek").build());
		list.add(A.builder().id(4L).name("Andrzej").build());
		list.add(A.builder().id(5L).name("Paweł").build());
		list.add(A.builder().id(6L).name("Janek").build());
		list.add(A.builder().id(7L).name("Andrzej").build());
		list.add(A.builder().id(8L).name("Paweł").build());
		list.add(A.builder().id(9L).name("Janek").build());
		list.add(A.builder().id(10L).name("Andrzej").build());
		list.add(A.builder().id(11L).name("Paweł").build());
		list.add(A.builder().id(12L).name("Janek").build());
		return list;
	}

	@GetMapping
	public List<Order> getOrders() {
		log.error("HERE");
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
