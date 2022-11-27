package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Comment;
import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.AddCommentRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.ItemRepository;
import com.example.softwaretesting.usecase.UserCommentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCommentService implements UserCommentUseCase {
	private final ItemRepository itemRepository;


	@Override
	public void addComment(ServiceUser user, AddCommentRequest request) {
		Item item = itemRepository.findByIdAndStatus(request.getProductId(), Item.Status.AVAILABLE).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
		item.getComments().add(new Comment(user, request.getComment()));
		itemRepository.save(item);
	}
}
