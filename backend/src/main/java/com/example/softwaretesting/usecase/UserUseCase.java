package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.request.RegisterRequest;
import com.example.softwaretesting.data.response.LoginResponse;

public interface UserUseCase {
	LoginResponse login(LoginRequest user);

	void register(RegisterRequest user);

}
