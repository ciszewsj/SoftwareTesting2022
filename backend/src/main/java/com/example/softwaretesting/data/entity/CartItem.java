package com.example.softwaretesting.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = {CascadeType.ALL})
	private Item item;

	private Integer numberOfItems;

	public CartItem(Item item) {
		this.item = item;
		this.numberOfItems = 0;
	}

	public Integer addNumberOfItems(Integer number) {
		log.error("Number of product is {} + {}", numberOfItems, number);
		numberOfItems = numberOfItems + number;
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}
		return numberOfItems;
	}
}
