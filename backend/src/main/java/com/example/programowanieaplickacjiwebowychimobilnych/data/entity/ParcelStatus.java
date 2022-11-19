package com.example.programowanieaplickacjiwebowychimobilnych.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class ParcelStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Status status;

	private Date date = new Date();

	@OneToOne
	private Address address;

	public ParcelStatus(Status status) {
		this.status = status;
	}

	public ParcelStatus(Address address) {
		this.status = Status.NOT_SENT;
		this.address = address;
	}

	public ParcelStatus(Status status, Address address) {
		this.status = status;
		this.address = address;
	}

	enum Status {
		NOT_SENT, NEW, RECEIVED, ROUTE, READY, DELIVERED
	}

}
