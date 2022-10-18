package com.example.metodywytwarzaniaoprogramowania.servies;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
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
		Product product = productRepository.getProductById(productId).orElseThrow(IllegalArgumentException::new);
		if (specialOffer.getId() != null && specialOffer.getProduct() != null) {
			throw new IllegalArgumentException();
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
				.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void deleteSpecialOffer(Long specialOfferId) {
		SpecialOffer specialOffer = specialOfferRepository.findById(specialOfferId).orElseThrow(IllegalArgumentException::new);
		specialOfferRepository.delete(specialOffer);
	}
}
