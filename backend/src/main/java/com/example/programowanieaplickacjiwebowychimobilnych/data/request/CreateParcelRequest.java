package com.example.programowanieaplickacjiwebowychimobilnych.data.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateParcelRequest {
	@NotEmpty
	@Length(min = 5, max = 5)
	String postCode;

	@NotEmpty
	@Length(min = 1, max = 50)
	String city;

	@NotEmpty
	@Length(max = 100)
	String street;

	@NotEmpty
	@Length(max = 25)
	String houseNumber;


	@NotEmpty
	@Length(min = 4, max = 50)
	private String name;

	@Email
	@NotEmpty
	@Length(min = 4, max = 50)
	private String email;

	@NotEmpty
	@Length(min = 4, max = 50)
	private String password;
}
