package com.example.programowanieaplickacjiwebowychimobilnych.usecase;

import com.example.programowanieaplickacjiwebowychimobilnych.data.request.LoginRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.RegisterRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.data.response.LoginResponse;

public interface CustomerUseCase {

	LoginResponse loginCustomer(LoginRequest request) throws Exception;

	void createCustomer(RegisterRequest request);
}
