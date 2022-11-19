package com.example.programowanieaplickacjiwebowychimobilnych.controller;

import com.example.programowanieaplickacjiwebowychimobilnych.data.request.LoginRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.RegisterRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.data.response.LoginResponse;
import com.example.programowanieaplickacjiwebowychimobilnych.usecase.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {


	private final CustomerUseCase customerUseCase;

	@PostMapping(value = "/login")
	public LoginResponse createAuthenticationToken(@RequestBody @Validated LoginRequest request)
			throws Exception {
		return customerUseCase.loginCustomer(request);
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerCustomer(@RequestBody @Validated RegisterRequest request) {
		log.info("Register request : {}", request);
		customerUseCase.createCustomer(request);
	}
}
