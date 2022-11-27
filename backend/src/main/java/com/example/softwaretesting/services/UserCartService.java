package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.CartItem;
import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.DeleteItemFromCartRequest;
import com.example.softwaretesting.data.request.PutItemToCartRequest;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.CartRepository;
import com.example.softwaretesting.repositories.ItemRepository;
import com.example.softwaretesting.usecase.UserCartUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCartService implements UserCartUseCase {
	private final CartRepository cartRepository;
	private final ItemRepository itemRepository;

	@Override
	public void payForCart(ServiceUser user) {
		Cart cart = getUserCart(user);
		if (cart.getItems().size() == 0 || cart.getItems().stream().anyMatch(item -> item.getItem().getStatus().equals(Item.Status.NOT_AVAILABLE))) {
			throw new ParametrizedException(ParametrizedException.Status.PRODUCT_IN_CART_IS_NOT_AVAILABLE);
		}
		cart.setStatus(Cart.Status.PAID);
		cartRepository.save(cart);
	}

	@Override
	public Cart getCart(ServiceUser user) {
		return getUserCart(user);
	}

	@Override
	public void putItemToCart(ServiceUser user, PutItemToCartRequest request) {
		Cart cart = getUserCart(user);
		Item item = itemRepository.findByIdAndStatus(request.getProductId(), Item.Status.AVAILABLE).orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_NOT_FOUND));
		CartItem cartItem = cart.getItems().stream().filter(cartItem2 -> cartItem2.getItem().equals(item)).findFirst().orElse(new CartItem(item));
		Integer numberOfItems = cartItem.addNumberOfItems(request.getNumberOfItems());
		if (numberOfItems <= 0) {
			cart.getItems().remove(cartItem);
		}
		cartRepository.save(cart);
	}

	@Override
	public void deleteItemFromCart(ServiceUser user, DeleteItemFromCartRequest request) {
		Cart cart = getUserCart(user);
		CartItem cartItem = cart.getItems().stream().filter(item -> item.getItem().getId().equals(request.getProductId())).findFirst().orElseThrow(new ParametrizedException(ParametrizedException.Status.PRODUCT_IS_NOT_IN_CART));
		Integer numberOfItems = cartItem.addNumberOfItems(-1 * request.getNumberOfItems());
		if (numberOfItems <= 0) {
			cart.getItems().remove(cartItem);
		}
		cartRepository.save(cart);

	}

	private Cart getUserCart(ServiceUser user) {
		return cartRepository.findFirstByUserIdAndStatus(user.getId(), Cart.Status.NOT_PAID).orElse(cartRepository.save(new Cart(user)));
	}
}
