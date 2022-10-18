package com.example.metodywytwarzaniaoprogramowania;

import com.example.metodywytwarzaniaoprogramowania.data.User;
import com.example.metodywytwarzaniaoprogramowania.repositories.UserRepository;
import com.example.metodywytwarzaniaoprogramowania.servies.UserService;
import com.example.metodywytwarzaniaoprogramowania.usecases.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	UserRepository userRepository;
	UserUseCase userUseCase;

	@BeforeEach
	public void setup() {
		userRepository = mock(UserRepository.class);
		userUseCase = new UserService(userRepository);
	}

	@Test
	void testCreateUser() {
		User user = new User();
		user.setName("Jan");
		user.setSurname("Pat");
		user.setBirth(new Date());
		user.setEmail("jjj@pw.ee");
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserByEmail(anyString())).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenReturn(user);

		userUseCase.createUser(user);

		verify(userRepository, times(1)).getUserByEmail(any());
		verify(userRepository, times(1)).save(any());
	}

	@Test
	void testCreateUserWhenPlayerWithEmailExists() {
		User user = new User();
		user.setName("Jan");
		user.setSurname("Pat");
		user.setBirth(new Date());
		user.setEmail("jjj@pw.ee");
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserByEmail(anyString())).thenReturn(Optional.of(user));

		assertThrows(IllegalArgumentException.class, () -> userUseCase.createUser(user));

		verify(userRepository, times(1)).getUserByEmail(any());
		verify(userRepository, times(0)).save(any());
	}

	@Test
	void testCreateUserWhenWrongData() {
		User user = new User();
		user.setId(1L);
		user.setName(null);
		user.setSurname(null);
		user.setBirth(new Date());
		user.setEmail(null);
		user.setPassword(null);
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);

		assertThrows(IllegalArgumentException.class, () -> userUseCase.createUser(user));
	}

	@Test
	void testUpdateUser() {
		User user = new User();
		user.setId(12L);
		user.setName("Jan");
		user.setSurname("Pat");
		user.setBirth(new Date());
		user.setEmail("jjj@pw.ee");
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));
		when(userRepository.save(any(User.class))).thenReturn(user);

		userUseCase.updateUser(user);

		verify(userRepository, times(1)).getUserById(any());
		verify(userRepository, times(1)).save(any());
	}

	@Test
	void testUpdateUserWhenNoPlayerWithId() {
		User user = new User();
		user.setId(12L);
		user.setName("Jan");
		user.setSurname("Pat");
		user.setBirth(new Date());
		user.setEmail("jjj@pw.ee");
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> userUseCase.updateUser(user));

		verify(userRepository, times(1)).getUserById(any());
		verify(userRepository, times(0)).save(any());
	}

	@Test
	void testUpdateUserWhenBadData() {
		User user = new User();
		user.setId(12L);
		user.setName(null);
		user.setSurname(null);
		user.setBirth(new Date());
		user.setEmail(null);
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));

		assertThrows(IllegalArgumentException.class, () -> userUseCase.updateUser(user));

		verify(userRepository, times(1)).getUserById(any());
		verify(userRepository, times(0)).save(any());
	}

	@Test
	void testDeleteUser() {
		Long userId = 12L;
		User user = new User();
		user.setId(userId);
		user.setName(null);
		user.setSurname(null);
		user.setBirth(new Date());
		user.setEmail(null);
		user.setPassword("Secret");
		user.setPostCode("96-330");
		user.setStreet("Wiśniowa 23");
		user.setCountry("Poland");
		user.setRole(User.UserRole.USER);
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));

		userUseCase.deleteUser(userId);

		verify(userRepository, times(1)).getUserById(any());
		verify(userRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteUserWhenNoPlayerWithId() {
		Long userId = 12L;
		when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> userUseCase.deleteUser(userId));

		verify(userRepository, times(1)).getUserById(any());
		verify(userRepository, times(0)).delete(any());
	}
}
