package com.example.metodywytwarzaniaoprogramowania.usecases;

import com.example.metodywytwarzaniaoprogramowania.data.User;

public interface UserUseCase {
	void createUser(User user);

	void updateUser(User user);

	void deleteUser(Long user);
}
