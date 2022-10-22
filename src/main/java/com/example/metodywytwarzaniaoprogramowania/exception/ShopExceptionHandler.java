package com.example.metodywytwarzaniaoprogramowania.exception;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ShopExceptionHandler {
	@ExceptionHandler(ShopException.class)
	public ResponseEntity<ErrorResponse> handleMobileCashDeskException(ShopException shopException) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ErrorResponse.builder()
						.message(shopException.getErrorTypes().toString())
						.build());
	}

	@Data
	@Builder
	public static class ErrorResponse {

		private String message;
		private String code;
	}
}
