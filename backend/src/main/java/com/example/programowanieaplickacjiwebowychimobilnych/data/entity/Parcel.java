package com.example.programowanieaplickacjiwebowychimobilnych.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Parcel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Date sendDate;

	@OneToOne
	private Customer sender;

	@OneToOne
	private Recipient recipient;

	@OneToMany
	private List<ParcelStatus> parcelStatus;

	@OneToOne
	private Address address;

	public Parcel(ParcelStatus parcelStatus) {
		this.parcelStatus = new ArrayList<>();
		this.parcelStatus.add(parcelStatus);
	}

	public Parcel() {

	}

	public void submitParcel() {
		sendDate = new Date();
	}
}
