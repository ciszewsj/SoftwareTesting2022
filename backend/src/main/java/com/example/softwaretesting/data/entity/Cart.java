package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private ServiceUser user;

	private Status status;

	@OneToMany
	private List<CartItem> items;

	public Cart(ServiceUser user) {
		this.user = user;
		this.status = Status.NOT_PAID;
		this.items = new ArrayList<>();
	}

	public enum Status {
		NOT_PAID, PAID, SENT
	}
}
