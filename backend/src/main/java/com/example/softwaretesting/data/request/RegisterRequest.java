package com.example.softwaretesting.data.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RegisterRequest {
	@Size(min = 3, max = 36)
	private String username;
	@Size(min = 3, max = 36)
	private String password;
}
