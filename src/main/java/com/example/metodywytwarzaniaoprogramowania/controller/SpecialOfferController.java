package com.example.metodywytwarzaniaoprogramowania.controller;


import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
import com.example.metodywytwarzaniaoprogramowania.usecases.SpecialOfferUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialoffer")
@RequiredArgsConstructor
@Slf4j
public class SpecialOfferController {
	private final SpecialOfferUseCase specialOfferUseCase;

	@GetMapping
	public List<SpecialOffer> getSpecialOffers() {
		log.info("getSpecialOffers");
		return specialOfferUseCase.getSpecialOffers();
	}

	@GetMapping("/{productId}")
	public SpecialOffer getCurrentBestSpecialOfferForProduct(@PathVariable Long productId) {
		log.info("getCurrentBestSpecialOfferForProduct: {}", productId);
		return specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId);
	}

	@PostMapping("/{productId}")
	public SpecialOffer addSpecialOfferForProduct(@RequestBody SpecialOffer specialOffer, @PathVariable Long productId) {
		log.info("addSpecialOfferForProduct: {} , {}", specialOffer, productId);
		return specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId);
	}

	@DeleteMapping("/{specialOfferId}")
	public void deleteSpecialOffer(@PathVariable Long specialOfferId) {
		log.info("deleteSpecialOffer: {}", specialOfferId);
		specialOfferUseCase.deleteSpecialOffer(specialOfferId);
	}

}
