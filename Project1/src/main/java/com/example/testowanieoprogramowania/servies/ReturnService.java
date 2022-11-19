package com.example.testowanieoprogramowania.servies;

import com.example.testowanieoprogramowania.data.Order;
import com.example.testowanieoprogramowania.data.Return;
import com.example.testowanieoprogramowania.exception.ShopErrorTypes;
import com.example.testowanieoprogramowania.exception.ShopException;
import com.example.testowanieoprogramowania.repositories.OrderRepository;
import com.example.testowanieoprogramowania.repositories.ReturnRepository;
import com.example.testowanieoprogramowania.usecases.ReturnUseCase;
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
        Order order = orderRepository.getOrderByStatusAndId(Order.Status.DELIVERED, orderId).orElseThrow(new ShopException(ShopErrorTypes.ORDER_NOT_FOUND));
        if (returnOrder != null) {
            Return newReturn = new Return();
            newReturn.setDescription(returnOrder);
            newReturn.setStatus(Return.ReturnStatus.REPORTED);
            newReturn.setOrder(order);
            return returnRepository.save(newReturn);
        } else {
            throw new ShopException(ShopErrorTypes.ILLEGAL_REQUEST_BODY);
        }
    }

    @Override
    public List<Return> getReturns(Long userId) {
        return returnRepository.getAllByOrderUserId(userId);
    }

    @Override
    public void changeReturnStatus(Long returnOrderId, Return.ReturnStatus returnStatus) {
        Return returnOrder = returnRepository.findById(returnOrderId).orElseThrow(new ShopException(ShopErrorTypes.RETURN_NOT_FOUND));
        if (returnStatus != Return.ReturnStatus.REPORTED) {
            returnOrder.setStatus(returnStatus);
            returnRepository.save(returnOrder);
        } else {
            throw new ShopException(ShopErrorTypes.ILLEGAL_RETURN_STATUS_CHANGE);
        }

    }
}
