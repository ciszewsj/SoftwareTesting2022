package com.example.softwaretesting.services;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	private final UserRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ServiceUser customer = customerRepository.findByName(username).orElse(null);
		if (customer != null) {
			return new User(customer.getUsername(), customer.getPassword(), customer.getAuthorities());
		}
		throw new UsernameNotFoundException("User not found with username: " + username);

	}

}