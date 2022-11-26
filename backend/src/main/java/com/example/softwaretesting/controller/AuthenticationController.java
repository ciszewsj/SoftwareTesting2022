package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.request.RegisterRequest;
import com.example.softwaretesting.data.response.LoginResponse;
import com.example.softwaretesting.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {
	private final UserUseCase userUseCase;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest user) {
		log.info("Login request : {} {}", user.getUsername(), user.getPassword());
		return userUseCase.login(user);
	}

	@PostMapping("/register")
	public void register(@RequestBody @Valid RegisterRequest user) {
		log.info("Login request : {} {}", user.getUsername(), user.getPassword());
		userUseCase.register(user);
	}
}
