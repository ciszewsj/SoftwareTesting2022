package com.example.metodywytwarzaniaoprogramowania.repositories;

import com.example.metodywytwarzaniaoprogramowania.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> getOrderByStatusAndUserId(Order.Status status, Long user);

	Optional<Order> getOrderByStatusAndId(Order.Status status, Long orderId);

}
