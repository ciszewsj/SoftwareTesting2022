package com.example.metodywytwarzaniaoprogramowania;

import com.example.metodywytwarzaniaoprogramowania.data.*;
import com.example.metodywytwarzaniaoprogramowania.repositories.OrderRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.SpecialOfferRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.UserRepository;
import com.example.metodywytwarzaniaoprogramowania.servies.OrderService;
import com.example.metodywytwarzaniaoprogramowania.usecases.OrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	OrderRepository orderRepository;
	UserRepository userRepository;
	ProductRepository productRepository;
	SpecialOfferRepository specialOfferRepository;

	OrderUseCase orderUseCase;

	@BeforeEach
	public void setup() {
		specialOfferRepository = mock(SpecialOfferRepository.class);
		productRepository = mock(ProductRepository.class);
		userRepository = mock(UserRepository.class);
		orderRepository = mock(OrderRepository.class);
		orderUseCase = new OrderService(productRepository, orderRepository, userRepository, specialOfferRepository);
	}

	@Test
	void testAddProductToOrderWhenOrderExist() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Long productId = 1L;
		Long orderId = 1L;
		Product product = new Product();
		product.setId(productId);
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(Order.Status.NOT_PAID);
		order.setProducts(new ArrayList<>());
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));
		when(orderRepository.getOrderByStatusAndUserId(any(), anyLong())).thenReturn(Optional.of(order));

		orderUseCase.addProductToOrder(productId, userId);

		verify(userRepository, times(1)).getUserById(any());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(1)).getOrderByStatusAndUserId(any(), anyLong());
	}

	@Test
	void testAddProductToOrderWhenOrderWithNotPaidStatusNotExist() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));
		when(orderRepository.getOrderByStatusAndUserId(any(), anyLong())).thenReturn(Optional.empty());

		orderUseCase.addProductToOrder(productId, userId);

		verify(userRepository, times(1)).getUserById(any());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(1)).getOrderByStatusAndUserId(any(), anyLong());
	}

	@Test
	void testAddProductToOrderWhenUserNotExist() {
		Long userId = 1L;
		Long productId = 1L;
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.addProductToOrder(productId, userId));

		verify(userRepository, times(1)).getUserById(any());
		verify(productRepository, times(0)).getProductById(any());
		verify(orderRepository, times(0)).getOrderByStatusAndUserId(any(), anyLong());
	}

	@Test
	void testAddProductToOrderWhenWrongData() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Long productId = 1L;
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.addProductToOrder(productId, userId));

		verify(userRepository, times(1)).getUserById(any());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(0)).getOrderByStatusAndUserId(any(), anyLong());
	}

	@Test
	void testDeleteProductFromOrder() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		order.getProducts().add(product);
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		orderUseCase.deleteProductFromOrder(productId, orderId);

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(1)).save(any());
	}

	@Test
	void testDeleteProductFromOrderWhenNoProductOnList() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.deleteProductFromOrder(productId, orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testDeleteProductFromOrderWhenProductNotExist() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.deleteProductFromOrder(productId, orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(productRepository, times(1)).getProductById(any());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testDeleteProductFromOrderWhenOrderNotExist() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.deleteProductFromOrder(productId, orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(productRepository, times(0)).getProductById(any());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testGetAllOrders() {
		Order orderA = new Order();
		orderA.setId(1L);
		Order orderB = new Order();
		orderB.setId(2L);
		Order orderC = new Order();
		orderC.setId(2L);
		when(orderRepository.findAll()).thenReturn(List.of(orderA, orderB, orderC));

		List<Order> orderList = orderUseCase.getAllOrders();

		assertEquals(3, orderList.size());
	}

	@Test
	void testGetShoppingCart() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Order order = new Order();
		order.setId(1L);
		order.setUser(user);
		order.setStatus(Order.Status.NOT_PAID);
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));

		Order returnOrder = orderUseCase.getShoppingCart(userId);

		assertEquals(order, returnOrder);
	}

	@Test
	void testGetShoppingCartWhenOrderWithUnpaidStatusNotExist() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Order order = null;
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.empty());

		Order returnOrder = orderUseCase.getShoppingCart(userId);

		assertEquals(order, returnOrder);
	}

	@Test
	void testPayForOrder() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setAmount(1);
		productA.setId(1L);
		Product productB = new Product();
		productB.setAmount(2);
		productA.setId(2L);
		order.getProducts().addAll(List.of(productA, productB));
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));

		orderUseCase.payForOrder(orderId);

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(orderRepository, times(1)).save(any());
		verify(productRepository, times(2)).save(any());
	}

	@Test
	void testPayForOrderWhenNoProducts() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setAmount(0);
		productA.setId(1L);
		Product productB = new Product();
		productB.setAmount(2);
		productA.setId(2L);
		order.getProducts().addAll(List.of(productA, productB));
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.of(order));

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.payForOrder(orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(orderRepository, times(0)).save(any());
		verify(productRepository, times(0)).save(any());

	}

	@Test
	void testPayForOrderWhenOrderWithStatusUnpaidAndIdNotExist() {
		Long orderId = 1L;
		when(orderRepository.getOrderByStatusAndId(any(), anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.payForOrder(orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), anyLong());
		verify(orderRepository, times(0)).save(any());
	}

	@ParameterizedTest
	@EnumSource(value = Order.Status.class, names = {"NEW", "PROCESSED", "SENT", "DELIVERD"})
	void testChangeOrderStatus(Order.Status status) {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(Order.Status.NEW);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

		orderUseCase.changeOrderStatus(orderId, status);

		verify(orderRepository, times(1)).findById(anyLong());
		verify(orderRepository, times(1)).save(any());
	}

	@Test
	void testChangeOrderStatusWhenStatusOfOrderIsNotPaid() {
		Order.Status status = Order.Status.DELIVERD;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(Order.Status.NOT_PAID);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.changeOrderStatus(orderId, status));

		verify(orderRepository, times(1)).findById(anyLong());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testChangeOrderStatusWhenStatusTryToChangeOnNotPaid() {
		Order.Status status = Order.Status.NOT_PAID;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(Order.Status.NEW);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.changeOrderStatus(orderId, status));

		verify(orderRepository, times(1)).findById(anyLong());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testChangeOrderStatusWhenOrderNotExist() {
		Order.Status status = Order.Status.DELIVERD;
		Long orderId = 1L;
		when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.changeOrderStatus(orderId, status));

		verify(orderRepository, times(1)).findById(anyLong());
		verify(orderRepository, times(0)).save(any());
	}

	@Test
	void testGetOrderPrice() {
		Long priceA = 1000L;
		Long priceB = 1299L;
		Long priceC = 109L;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setId(1L);
		productA.setPrice(priceA);
		Product productB = new Product();
		productB.setId(2L);
		productB.setPrice(priceB);
		Product productC = new Product();
		productC.setId(3L);
		productC.setPrice(priceC);
		order.getProducts().addAll(List.of(productA, productB, productC));
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(anyLong(), any(), any()))
				.thenReturn(Optional.empty());

		Long returnPrice = orderUseCase.getOrderPrice(orderId);

		assertEquals((priceA + priceB + priceC), (long) returnPrice);
	}

	@Test
	void testGetOrderPriceWhenSpecialOfferForProduct() {
		Long priceA = 1000L;
		Long priceB = 1299L;
		Long priceC = 109L;
		Long productAId = 1L;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setId(productAId);
		productA.setPrice(priceA);
		Product productB = new Product();
		productB.setId(2L);
		productB.setPrice(priceB);
		Product productC = new Product();
		productC.setId(3L);
		productC.setPrice(priceC);
		order.getProducts().addAll(List.of(productA, productB, productC));
		long specialOfferPriceForA = 100L;
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setPrice(specialOfferPriceForA);
		specialOffer.setProduct(productA);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(eq(productAId), any(), any()))
				.thenReturn(Optional.of(specialOffer));

		Long returnPrice = orderUseCase.getOrderPrice(orderId);

		assertEquals((specialOfferPriceForA + priceB + priceC), (long) returnPrice);
	}

	@Test
	void testGetOrderPriceWhenSpecialOfferIsWorseForProduct() {
		Long priceA = 1000L;
		Long priceB = 1299L;
		Long priceC = 109L;
		Long productAId = 1L;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setId(productAId);
		productA.setPrice(priceA);
		Product productB = new Product();
		productB.setId(2L);
		productB.setPrice(priceB);
		Product productC = new Product();
		productC.setId(3L);
		productC.setPrice(priceC);
		order.getProducts().addAll(List.of(productA, productB, productC));
		long specialOfferPriceForA = 10000L;
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setPrice(specialOfferPriceForA);
		specialOffer.setProduct(productA);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(anyLong(), any(), any()))
				.thenReturn(Optional.empty());
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(eq(productAId), any(), any()))
				.thenReturn(Optional.of(specialOffer));

		Long returnPrice = orderUseCase.getOrderPrice(orderId);

		assertEquals((priceA + priceB + priceC), (long) returnPrice);
	}

	@Test
	void testGetOrderPriceWhenProductHaveNoId() {
		Long priceA = 1000L;
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		Product productA = new Product();
		productA.setPrice(priceA);
		order.getProducts().addAll(List.of(productA));
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.getOrderPrice(orderId));

		verify(orderRepository, times(1)).findById(anyLong());
		verify(specialOfferRepository, times(0)).getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(anyLong(), any(), any());
	}

	@Test
	void testGetOrderPriceWhenNoProductsInOrder() {
		Long orderId = 1L;
		Order order = new Order();
		order.setId(orderId);
		long expectedValue = 0L;
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

		Long returnPrice = orderUseCase.getOrderPrice(orderId);

		assertEquals(expectedValue, (long) returnPrice);
	}

	@Test
	void testGetOrderPriceWhenOrderNotExist() {
		Long orderId = 1L;
		when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> orderUseCase.getOrderPrice(orderId));

		verify(orderRepository, times(1)).findById(anyLong());
		verify(specialOfferRepository, times(0)).getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(anyLong(), any(), any());
	}
}

