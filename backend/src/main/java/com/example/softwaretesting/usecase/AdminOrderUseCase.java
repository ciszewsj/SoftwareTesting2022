package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Cart;

import java.util.List;

public interface AdminOrderUseCase {
	List<Cart> getAllOrders();

	Cart getOrder(Long id);

	void sendOrder(Long id);
}
