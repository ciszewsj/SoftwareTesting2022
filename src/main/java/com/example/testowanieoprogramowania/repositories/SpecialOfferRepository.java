package com.example.testowanieoprogramowania.repositories;

import com.example.testowanieoprogramowania.data.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	Optional<SpecialOffer> getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(Long productId, Date startDate, Date stopDate);
}
