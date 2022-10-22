package com.example.testowanieoprogramowania.usecases;

import com.example.testowanieoprogramowania.data.SpecialOffer;

import java.util.List;

public interface SpecialOfferUseCase {
	SpecialOffer addSpecialOfferForProduct(SpecialOffer specialOffer, Long product);

	List<SpecialOffer> getSpecialOffers();

	SpecialOffer getCurrentBestSpecialOfferForProduct(Long id);

	void deleteSpecialOffer(Long specialOfferId);
}
