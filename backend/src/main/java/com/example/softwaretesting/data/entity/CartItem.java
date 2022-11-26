package com.example.softwaretesting.data.entity;

import javax.persistence.*;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Item item;

	private Integer numberOfItems;
}
