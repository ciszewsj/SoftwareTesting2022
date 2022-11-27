package com.example.softwaretesting.repositories;

import com.example.softwaretesting.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByIdAndStatus(Long id, Item.Status status);
}
