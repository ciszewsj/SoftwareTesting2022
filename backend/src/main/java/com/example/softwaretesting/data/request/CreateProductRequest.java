package com.example.softwaretesting.data.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateProductRequest {

	@Size(max = 100)
	private String name;

	private Long price;

	private Boolean available = true;
}
