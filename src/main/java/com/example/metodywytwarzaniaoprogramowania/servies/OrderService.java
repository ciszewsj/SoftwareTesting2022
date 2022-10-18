package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.Order;
import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
import com.example.metodywytwarzaniaoprogramowania.data.User;
import com.example.metodywytwarzaniaoprogramowania.repositories.OrderRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.SpecialOfferRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.UserRepository;
import com.example.metodywytwarzaniaoprogramowania.usecases.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements OrderUseCase {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final SpecialOfferRepository specialOfferRepository;

	@Override
	public void addProductToOrder(Long productId, Long userId) {
		User user = userRepository.getUserById(userId).orElseThrow(IllegalArgumentException::new);
		Product product = productRepository.getProductById(productId).orElseThrow(IllegalArgumentException::new);
		Order order = orderRepository.getOrderByStatusAndUserId(Order.Status.NOT_PAID, user.getId()).orElseGet(() -> {
			Order newOrder = new Order();
			newOrder.setProducts(new ArrayList<>());
			newOrder.setUser(user);
			newOrder.setStatus(Order.Status.NOT_PAID);
			newOrder.setProducts(new ArrayList<>());
			return newOrder;
		});
		order.getProducts().add(product);
		orderRepository.save(order);

	}

	@Override
	public void deleteProductFromOrder(Long productId, Long orderId) {
		Order order = orderRepository.getOrderByStatusAndId(Order.Status.NOT_PAID, orderId).orElseThrow(IllegalArgumentException::new);
		Product product = productRepository.getProductById(productId).orElseThrow(IllegalArgumentException::new);
		if (!order.getProducts().contains(product)) {
			throw new IllegalArgumentException();
		}
		order.getProducts().remove(product);
		orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getShoppingCart(Long user) {
		return orderRepository.getOrderByStatusAndId(Order.Status.NOT_PAID, user).orElse(null);
	}

	@Override
	public void payForOrder(Long orderId) {
		Order order = orderRepository.getOrderByStatusAndId(Order.Status.NOT_PAID, orderId).orElseThrow(IllegalArgumentException::new);
		for (Product product : order.getProducts()) {
			try {
				product.getFromShelf();
			} catch (IllegalStateException e) {
				throw new IllegalArgumentException();
			}
			productRepository.save(product);
		}
		order.setStatus(Order.Status.NEW);
		order.setPaid(new Date());
		orderRepository.save(order);
	}

	@Override
	public void changeOrderStatus(Long orderId, Order.Status status) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		if (order.getStatus() != Order.Status.NOT_PAID && status != Order.Status.NOT_PAID) {
			order.setStatus(status);
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Long getOrderPrice(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		Date now = new Date();
		long price = 0;
		for (Product product : order.getProducts()) {
			if (product.getId() == null) {
				throw new IllegalArgumentException();
			}
			SpecialOffer specialOffer = specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(product.getId(), now, now)
					.orElse(null);
			if (specialOffer == null || specialOffer.getPrice() > product.getPrice()) {
				price += product.getPrice();
			} else {
				price += specialOffer.getPrice();
			}
		}
		return price;
	}
}
