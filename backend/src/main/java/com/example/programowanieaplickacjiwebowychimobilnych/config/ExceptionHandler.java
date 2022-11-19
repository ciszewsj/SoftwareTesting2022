package com.example.programowanieaplickacjiwebowychimobilnych.config;

import com.example.programowanieaplickacjiwebowychimobilnych.exception.ParametrizedException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class
ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(ParametrizedException.class)
	public ResponseEntity<ErrorResponse> handleMobileCashDeskException(ParametrizedException parametrizedException) {
		return ResponseEntity.status(parametrizedException.getStatus())
				.body(ErrorResponse.builder()
						.code(parametrizedException.getStatus().toString())
						.message(parametrizedException.getMessage())
						.build());
	}

	@Data
	@Builder
	public static class ErrorResponse {

		private String message;
		private String code;
	}
}
