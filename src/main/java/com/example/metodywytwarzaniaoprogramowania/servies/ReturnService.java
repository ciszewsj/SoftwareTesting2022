package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.Order;
import com.example.metodywytwarzaniaoprogramowania.data.Return;
import com.example.metodywytwarzaniaoprogramowania.repositories.OrderRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.ReturnRepository;
import com.example.metodywytwarzaniaoprogramowania.usecases.ReturnUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnService implements ReturnUseCase {
	private final ReturnRepository returnRepository;
	private final OrderRepository orderRepository;


	@Override
	public Return createReturn(String returnOrder, Long orderId) {
		Order order = orderRepository.getOrderByStatusAndId(Order.Status.DELIVERD, orderId).orElseThrow(IllegalArgumentException::new);
		if (returnOrder != null) {
			Return newReturn = new Return();
			newReturn.setDescription(returnOrder);
			newReturn.setStatus(Return.ReturnStatus.REPORTED);
			newReturn.setOrder(order);
			return returnRepository.save(newReturn);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Return> getReturns(Long userId) {
		return returnRepository.getAllByOrderUserId(userId);
	}

	@Override
	public void changeReturnStatus(Long returnOrderId, Return.ReturnStatus returnStatus) {
		Return returnOrder = returnRepository.findById(returnOrderId).orElseThrow(IllegalArgumentException::new);
		if (returnStatus != Return.ReturnStatus.REPORTED) {
			returnOrder.setStatus(returnStatus);
			returnRepository.save(returnOrder);
		} else {
			throw new IllegalArgumentException();
		}

	}
}
