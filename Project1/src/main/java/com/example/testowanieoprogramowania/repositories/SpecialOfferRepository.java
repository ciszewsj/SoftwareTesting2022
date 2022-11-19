package com.example.testowanieoprogramowania.repositories;

import com.example.testowanieoprogramowania.data.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
    List<SpecialOffer> getAllByProductIdOrderByPrice(Long productId);
}
