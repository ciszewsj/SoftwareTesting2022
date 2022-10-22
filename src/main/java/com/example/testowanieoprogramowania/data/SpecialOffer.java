package com.example.testowanieoprogramowania.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date start;
	
	@Temporal(TemporalType.DATE)
	private Date stop;

	private long price;

	@OneToOne
	private Product product;
}
