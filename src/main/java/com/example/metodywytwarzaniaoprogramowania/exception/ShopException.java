package com.example.metodywytwarzaniaoprogramowania.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Slf4j
public class ShopException extends RuntimeException implements Supplier<RuntimeException> {

	@Getter
	private ShopErrorTypes errorTypes;

	public ShopException(ShopErrorTypes errorTypes) {
		super();
		this.errorTypes = errorTypes;
	}

	@Override
	public RuntimeException get() {
		return this;
	}

	@Override
	public String toString() {
		return "Shop Exception with type : %s".formatted(errorTypes.toString());
	}
}
