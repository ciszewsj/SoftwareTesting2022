package com.example.testowanieoprogramowania;

import com.example.testowanieoprogramowania.data.User;
import com.example.testowanieoprogramowania.exception.ShopErrorTypes;
import com.example.testowanieoprogramowania.exception.ShopException;
import com.example.testowanieoprogramowania.repositories.UserRepository;
import com.example.testowanieoprogramowania.servies.UserService;
import com.example.testowanieoprogramowania.usecases.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail("jjj@pw.ee");
        user.setPassword("Secret");
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);
        when(userRepository.getUserByEmailIsLike(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        userUseCase.createUser(user);

        verify(userRepository, times(1)).getUserByEmailIsLike(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testCreateUserWhenPlayerWithEmailExists() {
        User user = new User();
        user.setName("Jan");
        user.setSurname("Pat");
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail("jjj@pw.ee");
        user.setPassword("Secret");
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);
        when(userRepository.getUserByEmailIsLike(anyString())).thenReturn(Optional.of(user));

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.createUser(user));

        assertEquals(ShopErrorTypes.ILLEGAL_REQUEST_BODY, shopException.getErrorTypes());
        verify(userRepository, times(1)).getUserByEmailIsLike(any());
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void testCreateUserWhenWrongData() {
        User user = new User();
        user.setId(1L);
        user.setName(null);
        user.setSurname(null);
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail(null);
        user.setPassword(null);
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.createUser(user));

        assertEquals(ShopErrorTypes.ILLEGAL_REQUEST_BODY, shopException.getErrorTypes());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setId(12L);
        user.setName("Jan");
        user.setSurname("Pat");
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
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
    void testUpdateUserWhenNoUserWithId() {
        User user = new User();
        user.setId(12L);
        user.setName("Jan");
        user.setSurname("Pat");
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail("jjj@pw.ee");
        user.setPassword("Secret");
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);
        when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.updateUser(user));

        assertEquals(ShopErrorTypes.USER_NOT_FOUND, shopException.getErrorTypes());
        verify(userRepository, times(1)).getUserById(any());
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void testUpdateUserWhenBadData() {
        User user = new User();
        user.setId(12L);
        user.setName(null);
        user.setSurname(null);
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail(null);
        user.setPassword("Secret");
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);
        when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.updateUser(user));

        assertEquals(ShopErrorTypes.USER_NOT_FOUND, shopException.getErrorTypes());
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
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
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
    void testDeleteUserWhenNoUserWithId() {
        Long userId = 12L;
        when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.deleteUser(userId));

        assertEquals(ShopErrorTypes.USER_NOT_FOUND, shopException.getErrorTypes());
        verify(userRepository, times(1)).getUserById(any());
        verify(userRepository, times(0)).delete(any());
    }

    @Test
    void testGetUser() {
        Long userId = 12L;
        User user = new User();
        user.setId(userId);
        user.setName(null);
        user.setSurname(null);
        Calendar date = Calendar.getInstance();
        date.set(1960, 2, 13);
        user.setBirth(date.getTime());
        user.setEmail(null);
        user.setPassword("Secret");
        user.setPostCode("96-330");
        user.setStreet("Wiśniowa 23");
        user.setCountry("Poland");
        user.setRole(User.UserRole.USER);

        when(userRepository.getUserById(anyLong())).thenReturn(Optional.of(user));

        User userFromServer = userUseCase.getUser(userId);

        assertEquals(user, userFromServer);
    }

    @Test
    void testGetUserWhenNoUserWithId() {
        Long userId = 12L;
        when(userRepository.getUserById(anyLong())).thenReturn(Optional.empty());

        ShopException shopException = assertThrows(ShopException.class, () -> userUseCase.getUser(userId));
        assertEquals(ShopErrorTypes.USER_NOT_FOUND, shopException.getErrorTypes());
        verify(userRepository, times(1)).getUserById(any());
    }
}
