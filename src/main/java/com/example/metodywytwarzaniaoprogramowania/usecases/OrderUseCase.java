package com.example.metodywytwarzaniaoprogramowania.usecases;

import com.example.metodywytwarzaniaoprogramowania.data.Order;

import java.util.List;

public interface OrderUseCase {

	void addProductToOrder(Long product, Long user);

	void deleteProductFromOrder(Long product, Long order);

	List<Order> getAllOrders();

	Order getShoppingCart(Long user);

	void payForOrder(Long order);

	void changeOrderStatus(Long order, Order.Status status);

	Long getOrderPrice(Long order);

}
