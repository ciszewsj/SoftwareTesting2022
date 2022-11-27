package com.example.softwaretesting.data.request;

import lombok.Data;

@Data
public class DeleteItemFromCartRequest {
	private Long productId;
	private Integer numberOfItems;
}
