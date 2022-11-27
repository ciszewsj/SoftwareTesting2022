package com.example.softwaretesting.data.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AddCommentRequest {
	private Long productId;

	@Size(max = 100)
	@NotEmpty
	private String comment;
}
