package com.example.metodywytwarzaniaoprogramowania.usecases;

import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;

import java.util.List;

public interface SpecialOfferUseCase {
	SpecialOffer addSpecialOfferForProduct(SpecialOffer specialOffer, Long product);

	List<SpecialOffer> getSpecialOffers();

	SpecialOffer getCurrentBestSpecialOfferForProduct(Long id);

	void deleteSpecialOffer(Long specialOfferId);
}
