package com.example.testowanieoprogramowania.usecases;

import com.example.testowanieoprogramowania.data.Return;

import java.util.List;

public interface ReturnUseCase {
	Return createReturn(String returnOrder, Long order);

	List<Return> getReturns(Long user);

	void changeReturnStatus(Long returnOrder, Return.ReturnStatus returnStatus);
}
