package com.example.programowanieaplickacjiwebowychimobilnych.data.response;

import lombok.Data;

@Data
public class LoginResponse {

	private final String jwttoken;
	private final String role;
}