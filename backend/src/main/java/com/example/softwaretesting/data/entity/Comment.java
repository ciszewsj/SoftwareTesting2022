package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private ServiceUser user;

	@Length(max = 100)
	private String comment;

	public Comment(ServiceUser user, String comment) {
		this.user = user;
		this.comment = comment;
	}
}
