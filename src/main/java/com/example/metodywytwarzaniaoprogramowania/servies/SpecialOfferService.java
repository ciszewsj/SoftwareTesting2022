package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
import com.example.metodywytwarzaniaoprogramowania.exception.ShopErrorTypes;
import com.example.metodywytwarzaniaoprogramowania.exception.ShopException;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.SpecialOfferRepository;
import com.example.metodywytwarzaniaoprogramowania.usecases.SpecialOfferUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SpecialOfferService implements SpecialOfferUseCase {
	private final SpecialOfferRepository specialOfferRepository;
	private final ProductRepository productRepository;

	@Override
	public SpecialOffer addSpecialOfferForProduct(SpecialOffer specialOffer, Long productId) {
		Product product = productRepository.getProductById(productId).orElseThrow(new ShopException(ShopErrorTypes.PRODUCT_NOT_FOUND));
		if (specialOffer.getId() != null || specialOffer.getPrice() <= 0) {
			throw new ShopException(ShopErrorTypes.ILLEGAL_REQUEST_BODY);
		} else {
			specialOffer.setProduct(product);
			return specialOfferRepository.save(specialOffer);
		}
	}

	@Override
	public List<SpecialOffer> getSpecialOffers() {
		return specialOfferRepository.findAll();
	}

	@Override
	public SpecialOffer getCurrentBestSpecialOfferForProduct(Long productId) {
		Date now = new Date();
		return specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(productId, now, now)
				.orElseThrow(new ShopException(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND));
	}

	@Override
	public void deleteSpecialOffer(Long specialOfferId) {
		SpecialOffer specialOffer = specialOfferRepository.findById(specialOfferId).orElseThrow(new ShopException(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND));
		specialOfferRepository.delete(specialOffer);
	}
}
