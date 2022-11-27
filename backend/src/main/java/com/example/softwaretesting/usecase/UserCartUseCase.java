package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.Cart;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.DeleteItemFromCartRequest;
import com.example.softwaretesting.data.request.PutItemToCartRequest;

public interface UserCartUseCase {
	void payForCart(ServiceUser user);

	Cart getCart(ServiceUser user);

	void putItemToCart(ServiceUser user, PutItemToCartRequest request);

	void deleteItemFromCart(ServiceUser user, DeleteItemFromCartRequest request);
}
