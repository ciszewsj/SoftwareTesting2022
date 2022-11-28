package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Cart;
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
	public Cart getOrder(Long id) {
		return cartRepository.findByIdAndStatusOrStatus(id, Cart.Status.PAID, Cart.Status.SENT).orElseThrow(new ParametrizedException(ParametrizedException.Status.ORDER_NOT_FOUND));
	}

	@Override
	public void sendOrder(Long id) {
		Cart order = cartRepository.findByIdAndStatus(id, Cart.Status.PAID).orElseThrow(new ParametrizedException(ParametrizedException.Status.ORDER_NOT_FOUND));
		order.setStatus(Cart.Status.SENT);
		log.error("oreder status {} ", order.getStatus());
		cartRepository.save(order);
	}
}
