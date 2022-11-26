package com.example.softwaretesting.services;

import com.example.softwaretesting.config.JwtTokenUtil;
import com.example.softwaretesting.data.entity.Role;
import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.request.RegisterRequest;
import com.example.softwaretesting.data.response.LoginResponse;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.RoleRepository;
import com.example.softwaretesting.repositories.UserRepository;
import com.example.softwaretesting.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserUseCase {
	private final UserRepository customerRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public LoginResponse login(LoginRequest user) {
		try {
			authenticate(user.getUsername(), user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParametrizedException(ParametrizedException.Status.USER_NOT_FOUND);
		}
		UserDetails userDetails = customerRepository.findByName(user.getUsername()).orElseThrow(new ParametrizedException(ParametrizedException.Status.USER_NOT_FOUND));
		String token = jwtTokenUtil.generateToken(userDetails);
		return LoginResponse.builder().token(token).build();
	}

	@Override
	public void register(RegisterRequest user) {
		if (customerRepository.findByName(user.getUsername()).isPresent()) {
			throw new ParametrizedException(ParametrizedException.Status.USER_ALREADY_EXISTS);
		}
		Role role = roleRepository.findByRole("USER");
		ServiceUser customer = new ServiceUser();
		customer.setName(user.getUsername());
		customer.setRoles(List.of(role));

		customer.setPassword(passwordEncoder.encode(user.getPassword()));
		log.debug("Customer to save : {}", customer);
		customerRepository.save(customer);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.warn(e.toString());
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.warn(e.toString());
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
