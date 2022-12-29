package com.example.softwaretesting.data.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateProductRequest {

	@Size(max = 100)
	@NotEmpty
	private String name;

	private Long price;

	private Boolean available = true;
}
