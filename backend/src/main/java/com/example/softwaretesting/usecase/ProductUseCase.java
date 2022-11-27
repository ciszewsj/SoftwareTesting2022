package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Item;

import java.util.List;

public interface ProductUseCase {
	Item getItem(Long id);

	List<Item> getItems();
}
