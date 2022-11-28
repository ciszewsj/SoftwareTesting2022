package com.example.softwaretesting.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(max = 100)
	private String name;

	private Long price;

	private Status status;

	@OneToMany(cascade = {CascadeType.ALL})
	private List<Comment> comments = new ArrayList<>();

	public enum Status {
		AVAILABLE, NOT_AVAILABLE
	}
}
