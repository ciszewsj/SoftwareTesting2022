package com.example.softwaretesting.data.request;

import lombok.Data;

@Data
public class PutItemToCartRequest {
	private Long productId;
	private Integer numberOfItems;
}
