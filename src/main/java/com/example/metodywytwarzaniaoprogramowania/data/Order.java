package com.example.metodywytwarzaniaoprogramowania.data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Status status;

	private Date paid;

	@OneToOne
	private User user;

	@OneToMany
	private List<Product> products = new ArrayList<>();

	public enum Status {
		NOT_PAID, NEW, PROCESSED, SENT, DELIVERD
	}
}
