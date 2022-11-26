package com.example.softwaretesting.config;

import com.example.softwaretesting.data.entity.ServiceUser;
import com.example.softwaretesting.exception.ParametrizedException;
import com.example.softwaretesting.repositories.RoleRepository;
import com.example.softwaretesting.repositories.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableTransactionManagement
public class JwtRequestFilter extends OncePerRequestFilter {

	private final UserRepository customerRepository;
	private final RoleRepository roleRepository;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	@Transactional
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = findUserByName(username);
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//				userDetails.getAuthorities().forEach(e -> log.error("ROLES : {}", e.getAuthority()));
				Collection<? extends GrantedAuthority> authorities = customerRepository.findUserRoles(username);
				authorities.forEach(e -> log.error("YESSSS : {}", e.getAuthority()));
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, authorities);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	@Transactional
	ServiceUser findUserByName(String username) {
		ServiceUser user = this.customerRepository.findByName(username).orElseThrow(new ParametrizedException(ParametrizedException.Status.USER_NOT_FOUND));
		return user;
	}

}
