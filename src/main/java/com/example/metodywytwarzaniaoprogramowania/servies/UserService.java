package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.User;
import com.example.metodywytwarzaniaoprogramowania.repositories.UserRepository;
import com.example.metodywytwarzaniaoprogramowania.usecases.UserUseCase;
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
				&& userRepository.getUserByEmail(user.getEmail()).isEmpty()) {
			userRepository.save(user);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void updateUser(User user) {
		if (userRepository.getUserById(user.getId()).isPresent() && user.getName() != null && user.getPassword() != null && user.getEmail() != null) {
			userRepository.save(user);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepository.getUserById(userId).orElseThrow(IllegalArgumentException::new);
		userRepository.delete(user);
	}
}
