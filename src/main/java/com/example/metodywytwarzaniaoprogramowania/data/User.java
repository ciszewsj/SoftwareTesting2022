package com.example.metodywytwarzaniaoprogramowania.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String surname;

	private Date birth;

	private String email;

	private String password;

	private String city;

	private String street;

	private String country;

	private String postCode;

	private UserRole role;

	public enum UserRole {
		USER, ADMIN
	}
}
