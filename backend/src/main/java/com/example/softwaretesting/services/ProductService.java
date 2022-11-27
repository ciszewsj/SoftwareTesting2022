package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.ItemRepository;
import com.example.softwaretesting.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService implements ProductUseCase {
	private final ItemRepository itemRepository;

	@Override
	public Item getItem(Long id) {
		return itemRepository.findByIdAndStatus(id, Item.Status.AVAILABLE).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
	}

	@Override
	public List<Item> getItems() {
		return itemRepository.findAll();
	}
}
