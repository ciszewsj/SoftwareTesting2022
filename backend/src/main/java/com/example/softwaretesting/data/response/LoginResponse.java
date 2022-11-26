package com.example.softwaretesting.data.response;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private String token;
}
