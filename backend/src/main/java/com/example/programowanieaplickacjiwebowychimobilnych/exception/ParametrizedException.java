package com.example.programowanieaplickacjiwebowychimobilnych.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ParametrizedException extends RuntimeException implements Supplier<RuntimeException> {

	@Getter
	private HttpStatus status;

	@Getter
	private String message;

	@Getter
	private List<String> errors;

	public ParametrizedException(String message) {
		super();
		this.status = HttpStatus.CONFLICT;
		this.message = message;
	}

	public ParametrizedException(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	@Override
	public RuntimeException get() {
		return this;
	}
}
