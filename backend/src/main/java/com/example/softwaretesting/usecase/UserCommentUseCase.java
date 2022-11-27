package com.example.softwaretesting.usecase;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.AddCommentRequest;

public interface UserCommentUseCase {
	void addComment(ServiceUser user, AddCommentRequest request);
}
