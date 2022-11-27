package com.example.softwaretesting.data.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PutItemToCartRequest {
	@NotEmpty
	private Long productId;
	@NotEmpty
	private Integer numberOfItems;
}
