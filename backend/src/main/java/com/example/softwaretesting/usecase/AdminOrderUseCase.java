package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.request.SendOrderRequest;

import java.util.List;

public interface AdminOrderUseCase {
	List<Cart> getAllOrders();

	void sendOrder(SendOrderRequest request);
}
