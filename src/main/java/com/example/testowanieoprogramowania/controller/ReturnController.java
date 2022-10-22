package com.example.testowanieoprogramowania.controller;

import com.example.testowanieoprogramowania.data.Return;
import com.example.testowanieoprogramowania.usecases.ReturnUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/return")
@RequiredArgsConstructor
@Slf4j
public class ReturnController {
	private final ReturnUseCase returnUseCase;

	@GetMapping("/{userId}")
	public List<Return> getReturns(@PathVariable Long userId) {
		log.info("getReturns: {}", userId);
		return returnUseCase.getReturns(userId);
	}

	@PostMapping("/{orderId}")
	public Return createReturn(@PathVariable Long orderId, @RequestBody String description) {
		log.info("createReturn: {} , {}", orderId, description);
		return returnUseCase.createReturn(description, orderId);
	}

	@PutMapping("/{returnOrderId}")
	public void changeReturnStatus(@RequestBody Long returnOrderId, @RequestBody Return.ReturnStatus returnStatus) {
		log.info("changeReturnStatus: {} , {}", returnOrderId, returnStatus);
		returnUseCase.changeReturnStatus(returnOrderId, returnStatus);
	}
}
