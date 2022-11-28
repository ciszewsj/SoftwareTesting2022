package com.example.softwaretesting.controller;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.AddCommentRequest;
import com.example.softwaretesting.usecase.UserCommentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/comment")
public class UserCommentController {
	private final UserCommentUseCase useCase;

	@PostMapping
	public void createComment(@AuthenticationPrincipal ServiceUser user,
	                          @RequestBody @Valid AddCommentRequest request) {
		log.error(user.getUsername());
		useCase.addComment(user, request);
	}
}
