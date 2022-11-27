package com.example.softwaretesting.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class ParametrizedException extends RuntimeException implements Supplier<RuntimeException> {

	@Getter
	private HttpStatus status;

	@Getter
	private Status error;


	public ParametrizedException(HttpStatus status, Status message) {
		super();
		this.status = status;
		this.error = message;
	}

	public ParametrizedException(Status message) {
		super();
		this.status = HttpStatus.CONFLICT;
		this.error = message;
	}


	public enum Status {
		USER_NOT_FOUND,
		USER_ALREADY_EXISTS,
		PRODUCT_IN_CART_IS_NOT_AVAILABLE,
		PRODUCT_IS_NOT_IN_CART,
		PRODUCT_NOT_FOUND,
		ORDER_NOT_FOUND,
		WRONG_DATA
	}

	@Override
	public RuntimeException get() {
		return this;
	}
}
