package com.example.metodywytwarzaniaoprogramowania.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Long price;

	private Integer amount;

	public void getFromShelf() {
		if (amount > 0) {
			amount--;
		} else {
			throw new IllegalStateException();
		}
	}

}
