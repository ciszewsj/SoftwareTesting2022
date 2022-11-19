package com.example.testowanieoprogramowania.servies;

import com.example.testowanieoprogramowania.data.Product;
import com.example.testowanieoprogramowania.data.SpecialOffer;
import com.example.testowanieoprogramowania.exception.ShopErrorTypes;
import com.example.testowanieoprogramowania.exception.ShopException;
import com.example.testowanieoprogramowania.repositories.ProductRepository;
import com.example.testowanieoprogramowania.repositories.SpecialOfferRepository;
import com.example.testowanieoprogramowania.usecases.SpecialOfferUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
        return specialOfferRepository.getAllByProductIdOrderByPrice(productId).stream()
                .peek(specialOffer -> log.warn(specialOffer.toString()))
                .filter(specialOffer -> now.compareTo(specialOffer.getStart()) >= 0)
                .filter(specialOffer -> now.compareTo(specialOffer.getStop()) <= 0)
                .min(Comparator.comparingLong(SpecialOffer::getPrice))
                .orElseThrow(new ShopException(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND));
    }

    @Override
    public void deleteSpecialOffer(Long specialOfferId) {
        SpecialOffer specialOffer = specialOfferRepository.findById(specialOfferId).orElseThrow(new ShopException(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND));
        specialOfferRepository.delete(specialOffer);
    }
}
