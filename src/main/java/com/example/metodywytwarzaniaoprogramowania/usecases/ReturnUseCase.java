package com.example.metodywytwarzaniaoprogramowania.usecases;

import com.example.metodywytwarzaniaoprogramowania.data.Return;

import java.util.List;

public interface ReturnUseCase {
	Return createReturn(String returnOrder, Long order);

	List<Return> getReturns(Long user);

	void changeReturnStatus(Long returnOrder, Return.ReturnStatus returnStatus);
}
