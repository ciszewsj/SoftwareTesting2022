package com.example.metodywytwarzaniaoprogramowania.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date start;

	private Date stop;

	private long price;

	@OneToOne
	private Product product;
}
