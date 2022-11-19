package com.example.testowanieoprogramowania.servies;

import com.example.testowanieoprogramowania.data.User;
import com.example.testowanieoprogramowania.exception.ShopErrorTypes;
import com.example.testowanieoprogramowania.exception.ShopException;
import com.example.testowanieoprogramowania.repositories.UserRepository;
import com.example.testowanieoprogramowania.usecases.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    @Override
    public void createUser(User user) {
        if (user.getId() == null && user.getName() != null
                && user.getPassword() != null
                && user.getEmail() != null
                && userRepository.getUserByEmailIsLike(user.getEmail()).isEmpty()) {
            userRepository.save(user);
        } else {
            throw new ShopException(ShopErrorTypes.ILLEGAL_REQUEST_BODY);
        }
    }

    @Override
    public void updateUser(User user) {
        if (userRepository.getUserById(user.getId()).isPresent() && user.getName() != null && user.getPassword() != null && user.getEmail() != null) {
            userRepository.save(user);
        } else {
            throw new ShopException(ShopErrorTypes.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.getUserById(userId).orElseThrow(new ShopException(ShopErrorTypes.USER_NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.getUserById(userId).orElseThrow(new ShopException(ShopErrorTypes.USER_NOT_FOUND));
        return user;
    }
}
