package com.example.softwaretesting.repositories;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByStatusOrStatus(Cart.Status status1, Cart.Status status2);

	Optional<Cart> findByIdAndStatusOrStatus(Long id, Cart.Status status1, Cart.Status status2);

	Optional<Cart> findByIdAndStatus(Long id, Cart.Status status);

	Optional<Cart> findFirstByUserIdAndStatus(Long userId, Cart.Status status);

	Optional<Cart> findByIdAndUser(Long id, ServiceUser user);

	List<Cart> findByUser(ServiceUser user);
}
