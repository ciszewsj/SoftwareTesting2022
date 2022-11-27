package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.ServiceUser;

import java.util.List;

public interface UserOrderUseCase {
	List<Cart> getOrders(ServiceUser user);

	Cart getOrder(ServiceUser user, Long id);
}
