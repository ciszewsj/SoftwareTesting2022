package com.example.softwaretesting.repositories;

import com.example.softwaretesting.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
