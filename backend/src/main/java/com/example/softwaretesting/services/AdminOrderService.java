package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.request.SendOrderRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.CartRepository;
import com.example.softwaretesting.usecase.AdminOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminOrderService implements AdminOrderUseCase {
	private final CartRepository cartRepository;

	@Override
	public List<Cart> getAllOrders() {
		return cartRepository.findAllByStatusOrStatus(Cart.Status.PAID, Cart.Status.SENT);
	}

	@Override
	public void sendOrder(SendOrderRequest request) {
		Cart order = cartRepository.findByIdAndStatus(request.getOrderId(), Cart.Status.PAID).orElseThrow(new ParametrizedException(ParametrizedException.Status.ORDER_NOT_FOUND));
		order.setStatus(Cart.Status.SENT);
		cartRepository.save(order);
	}
}
