package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(max = 100)
	private String name;

	private Long price;

	private Status status;

	@OneToMany
	private List<Comment> comments;

	public Item() {
		this.comments = new ArrayList<>();
	}

	public enum Status {
		AVAILABLE, NOT_AVAILABLE
	}
}
