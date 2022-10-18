package com.example.metodywytwarzaniaoprogramowania.repositories;

import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	Optional<SpecialOffer> getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(Long productId, Date startDate, Date stopDate);

	List<SpecialOffer> findAllByProductId(Long productId);
}
