package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.CartRepository;
import com.example.softwaretesting.usecase.UserOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserOrderService implements UserOrderUseCase {
	private final CartRepository cartRepository;


	@Override
	public List<Cart> getOrders(ServiceUser user) {
		return cartRepository.findByUser(user);
	}

	@Override
	public Cart getOrder(ServiceUser user, Long id) {
		return cartRepository.findByIdAndUser(id, user).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
	}
}
