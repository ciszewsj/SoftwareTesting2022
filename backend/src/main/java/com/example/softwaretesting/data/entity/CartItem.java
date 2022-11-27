package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Item item;

	private Integer numberOfItems;

	public CartItem(Item item) {
		this.item = item;
		this.numberOfItems = 0;
	}

	public Integer addNumberOfItems(Integer number) {
		numberOfItems += number;
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}
		return numberOfItems;
	}
}
