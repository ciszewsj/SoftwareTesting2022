package com.example.softwaretesting.data.entity;

import com.example.softwaretesting.data.serializer.ServiceUserMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = ServiceUserMapper.class)
public class ServiceUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, name = "username")
	@Length(max = 100)
	private String name;

	@Length(max = 100)
	private String password;

	@ManyToMany(cascade = {CascadeType.ALL})
	private Collection<Role> roles = new HashSet<>();

	public ServiceUser() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + password;
	}
}
