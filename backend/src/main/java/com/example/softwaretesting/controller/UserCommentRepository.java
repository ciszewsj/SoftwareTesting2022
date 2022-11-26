package com.example.softwaretesting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/comment")
public class UserCommentRepository {

	@PostMapping
	public void createComment() {

	}
}
