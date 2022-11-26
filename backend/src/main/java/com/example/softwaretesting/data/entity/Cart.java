package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Status status;

	@OneToMany
	private List<CartItem> items;

	public enum Status {
		NOT_PAID, PAID, SENT
	}
}
