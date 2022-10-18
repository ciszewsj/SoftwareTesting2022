package com.example.metodywytwarzaniaoprogramowania.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Return {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private ReturnStatus status;

	@OneToOne
	private Order order;

	public enum ReturnStatus {
		REPORTED, CONSIDERED, ACCEPTED, DECLINED
	}
}
