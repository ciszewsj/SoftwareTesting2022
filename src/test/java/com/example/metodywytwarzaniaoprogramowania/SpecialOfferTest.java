package com.example.metodywytwarzaniaoprogramowania;

import com.example.metodywytwarzaniaoprogramowania.data.Product;
import com.example.metodywytwarzaniaoprogramowania.data.SpecialOffer;
import com.example.metodywytwarzaniaoprogramowania.repositories.ProductRepository;
import com.example.metodywytwarzaniaoprogramowania.repositories.SpecialOfferRepository;
import com.example.metodywytwarzaniaoprogramowania.servies.SpecialOfferService;
import com.example.metodywytwarzaniaoprogramowania.usecases.SpecialOfferUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecialOfferTest {
	SpecialOfferRepository specialOfferRepository;
	ProductRepository productRepository;

	SpecialOfferUseCase specialOfferUseCase;

	@BeforeEach
	public void setup() {
		specialOfferRepository = mock(SpecialOfferRepository.class);
		productRepository = mock(ProductRepository.class);
		specialOfferUseCase = new SpecialOfferService(specialOfferRepository, productRepository);
	}

	@Test
	void testAddSpecialOfferForProduct() {
		Long productId = 10L;
		Product product = new Product();
		product.setId(productId);
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setPrice(10L);
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));
		when(specialOfferRepository.save(any(SpecialOffer.class))).thenReturn(specialOffer);

		SpecialOffer returnSpecialOffer = specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId);

		assertEquals(product, returnSpecialOffer.getProduct());
	}

	@Test
	void testAddSpecialOfferForProductWhenProductNotExist() {
		Long productId = 10L;
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setPrice(10L);
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () ->
				specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId));

		verify(productRepository, times(1)).getProductById(anyLong());
		verify(specialOfferRepository, times(0)).save(any());
	}

	@Test
	void testAddSpecialOfferForProductWhenBadArguments() {
		Long productId = 10L;
		Product product = new Product();
		product.setId(productId);
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setProduct(product);
		specialOffer.setId(10L);
		specialOffer.setPrice(10L);
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		assertThrows(IllegalArgumentException.class, () ->
				specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId));

		verify(productRepository, times(1)).getProductById(anyLong());
		verify(specialOfferRepository, times(0)).save(any());
	}

	@Test
	void testGetSpecialOffers() {
		SpecialOffer specialOfferA = new SpecialOffer();
		specialOfferA.setId(1L);
		specialOfferA.setPrice(10L);
		SpecialOffer specialOfferB = new SpecialOffer();
		specialOfferB.setId(2L);
		specialOfferB.setPrice(100L);
		SpecialOffer specialOfferC = new SpecialOffer();
		specialOfferC.setId(3L);
		specialOfferC.setPrice(1000L);
		when(specialOfferRepository.findAll()).thenReturn(List.of(specialOfferA, specialOfferB, specialOfferC));

		List<SpecialOffer> specialOfferList = specialOfferUseCase.getSpecialOffers();

		assertEquals(3, specialOfferList.size());
	}

	@Test
	void testGetCurrentBestSpecialOfferForProduct() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setPrice(10L);
		specialOffer.setProduct(product);
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(any(), any(), any())).thenReturn(Optional.of(specialOffer));

		SpecialOffer returnSpecialOffer = specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId);

		assertEquals(specialOffer, returnSpecialOffer);
	}

	@Test
	void testGetCurrentBestSpecialOfferForProductWhenNoSpecialOfferForProduct() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		when(specialOfferRepository.getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(any(), any(), any())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId));

		verify(specialOfferRepository, times(1)).getTopByProductIdAndStartAfterAndStopBeforeOrderByPrice(any(), any(), any());
	}

	@Test
	void deleteSpecialOffer() {
		Long specialOfferId = 1L;
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(specialOfferId);
		when(specialOfferRepository.findById(anyLong())).thenReturn(Optional.of(specialOffer));

		specialOfferUseCase.deleteSpecialOffer(specialOfferId);

		verify(specialOfferRepository, times(1)).findById(any());
		verify(specialOfferRepository, times(1)).delete(any());
	}

	@Test
	void deleteSpecialOfferWhenSpecialOfferNotExist() {
		Long specialOfferId = 1L;
		when(specialOfferRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> specialOfferUseCase.deleteSpecialOffer(specialOfferId));

		verify(specialOfferRepository, times(1)).findById(any());
		verify(specialOfferRepository, times(0)).delete(any());
	}
}
