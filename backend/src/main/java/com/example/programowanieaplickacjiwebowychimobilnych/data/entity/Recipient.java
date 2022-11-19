package com.example.programowanieaplickacjiwebowychimobilnych.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Recipient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotBlank
	@Length(min = 4, max = 50)
	private String name;

	@Email
	@NotBlank
	@Length(min = 4, max = 50)
	private String email;
}
