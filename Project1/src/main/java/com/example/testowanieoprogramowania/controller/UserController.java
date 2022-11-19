package com.example.testowanieoprogramowania.controller;

import com.example.testowanieoprogramowania.data.User;
import com.example.testowanieoprogramowania.usecases.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserUseCase userUseCase;

    @PostMapping
    public void createUser(@RequestBody User user) {
        log.info("createUser: {}", user);
        userUseCase.createUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        log.info("updateUser: {}", user);
        userUseCase.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("deleteUser: {}", userId);
        userUseCase.deleteUser(userId);
    }
}
