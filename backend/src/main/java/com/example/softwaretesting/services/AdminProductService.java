package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.CreateProductRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.ItemRepository;
import com.example.softwaretesting.usecase.AdminProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminProductService implements AdminProductUseCase {
	private final ItemRepository itemRepository;

	@Override
	public Long create(CreateProductRequest request) {
		Item item = new Item();
		item.setName(request.getName());
		item.setPrice(request.getPrice());
		item.setStatus(request.getAvailable() ? Item.Status.AVAILABLE : Item.Status.NOT_AVAILABLE);
		item = itemRepository.save(item);
		return item.getId();
	}

	@Override
	public void setAvailable(Long id) {
		Item item = itemRepository.findById(id).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
		item.setStatus(Item.Status.AVAILABLE);
		itemRepository.save(item);
	}

	@Override
	public void deleteProduct(Long id) {
		Item item = itemRepository.findById(id).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
		item.setStatus(Item.Status.NOT_AVAILABLE);
		itemRepository.save(item);
	}

	@Override
	public List<Item> getAllProducts() {
		return itemRepository.findAll();
	}

	@Override
	public Item getProduct(Long id) {
		return itemRepository.findById(id).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
	}
}
