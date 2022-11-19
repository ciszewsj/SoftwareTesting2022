package com.example.testowanieoprogramowania.usecases;

import com.example.testowanieoprogramowania.data.User;

public interface UserUseCase {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    User getUser(Long userId);
}
