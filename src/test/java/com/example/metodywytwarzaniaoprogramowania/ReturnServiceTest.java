package com.example.metodywytwarzaniaoprogramowania;


import com.example.metodywytwarzaniaoprogramowania.data.Order;
import com.example.metodywytwarzaniaoprogramowania.data.Return;
import com.example.metodywytwarzaniaoprogramowania.data.User;
import com.example.metodywytwarzaniaoprogramowania.repositories.OrderRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.ReturnRepository;
import com.example.metodywytwarzaniaoprogramowania.servies.ReturnService;
import com.example.metodywytwarzaniaoprogramowania.usecases.ReturnUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReturnServiceTest {

	ReturnRepository returnRepository;
	OrderRepository orderRepository;
	ReturnUseCase returnUseCase;

	@BeforeEach
	public void setup() {
		returnRepository = mock(ReturnRepository.class);
		orderRepository = mock(OrderRepository.class);
		returnUseCase = new ReturnService(returnRepository, orderRepository);
	}

	@Test
	void testCreateReturn() {
		Long orderId = 1L;
		Order order = new Order();
		order.setStatus(Order.Status.DELIVERD);
		order.setId(orderId);
		String returnOrderString = "It's broken";
		Return returnOrder = new Return();
		returnOrder.setOrder(order);
		returnOrder.setStatus(Return.ReturnStatus.REPORTED);
		returnOrder.setDescription(returnOrderString);
		when(orderRepository.getOrderByStatusAndId(any(), any())).thenReturn(Optional.of(order));
		when(returnRepository.save(any(Return.class))).thenReturn(returnOrder);

		Return returnReturnOrder = returnUseCase.createReturn(returnOrderString, orderId);

		assertEquals(returnOrder, returnReturnOrder);
	}

	@Test
	void testCreateReturnWhenOrderWithStatusAndIdNotExists() {
		Long orderId = 1L;
		String returnOrderString = "It's broken";
		when(orderRepository.getOrderByStatusAndId(any(), any())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> returnUseCase.createReturn(returnOrderString, orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), any());
		verify(returnRepository, times(0)).save(any());
	}

	@Test
	void testCreateReturnWhenReturnOrderDescriptionIsNull() {
		Long orderId = 1L;
		Order order = new Order();
		order.setStatus(Order.Status.DELIVERD);
		order.setId(orderId);
		String returnOrderString = null;
		when(orderRepository.getOrderByStatusAndId(any(), any())).thenReturn(Optional.of(order));

		assertThrows(IllegalArgumentException.class, () -> returnUseCase.createReturn(returnOrderString, orderId));

		verify(orderRepository, times(1)).getOrderByStatusAndId(any(), any());
		verify(returnRepository, times(0)).save(any());
	}

	@Test
	void testGetReturns() {
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		Order orderA = new Order();
		orderA.setId(1L);
		orderA.setUser(user);
		Order orderB = new Order();
		orderB.setId(2L);
		orderB.setUser(user);
		Return returnA = new Return();
		returnA.setOrder(orderA);
		Return returnB = new Return();
		returnB.setOrder(orderB);
		when(returnRepository.getAllByOrderUserId(anyLong())).thenReturn(List.of(returnA, returnB));

		List<Return> returnList = returnUseCase.getReturns(userId);

		assertEquals(2, returnList.size());
	}

	@ParameterizedTest
	@EnumSource(value = Return.ReturnStatus.class, names = {"CONSIDERED", "ACCEPTED", "DECLINED"})
	void testChangeReturnStatus(Return.ReturnStatus status) {
		Long orderId = 1L;
		Return returnOrder = new Return();
		returnOrder.setStatus(status);
		returnOrder.setId(orderId);
		when(returnRepository.findById(anyLong())).thenReturn(Optional.of(returnOrder));
		when(returnRepository.save(any(Return.class))).thenReturn(returnOrder);

		returnUseCase.changeReturnStatus(orderId, status);

		verify(returnRepository, times(1)).findById(anyLong());
		verify(returnRepository, times(1)).save(any());
	}

	@Test
	void testChangeReturnStatusWhenNewStatusIsReported() {
		Long orderId = 1L;
		Return.ReturnStatus status = Return.ReturnStatus.REPORTED;
		Return returnOrder = new Return();
		returnOrder.setStatus(status);
		returnOrder.setId(orderId);
		when(returnRepository.findById(anyLong())).thenReturn(Optional.of(returnOrder));

		assertThrows(IllegalArgumentException.class, () -> returnUseCase.changeReturnStatus(orderId, status));

		verify(returnRepository, times(1)).findById(anyLong());
		verify(returnRepository, times(0)).save(any());
	}

	@Test
	void testChangeReturnStatusWhenReturnNotExist() {
		Long orderId = 1L;
		Return.ReturnStatus status = Return.ReturnStatus.REPORTED;
		when(returnRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> returnUseCase.changeReturnStatus(orderId, status));

		verify(returnRepository, times(1)).findById(anyLong());
		verify(returnRepository, times(0)).save(any());
	}
}
