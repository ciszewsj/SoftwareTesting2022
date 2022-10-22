package com.example.testowanieoprogramowania;

import com.example.testowanieoprogramowania.data.Product;
import com.example.testowanieoprogramowania.data.SpecialOffer;
import com.example.testowanieoprogramowania.exception.ShopErrorTypes;
import com.example.testowanieoprogramowania.exception.ShopException;
import com.example.testowanieoprogramowania.repositories.ProductRepository;
import com.example.testowanieoprogramowania.repositories.SpecialOfferRepository;
import com.example.testowanieoprogramowania.servies.SpecialOfferService;
import com.example.testowanieoprogramowania.usecases.SpecialOfferUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.example.testowanieoprogramowania.Utils.getTimeWithAdd;
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

	@ParameterizedTest
	@ValueSource(longs = {Long.MIN_VALUE, -100, 0})
	void testAddSpecialOfferForProductWhenWrongPrice(long price) {
		Long productId = 10L;
		Product product = new Product();
		product.setId(productId);
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setPrice(price);
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.of(product));

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId));

		assertEquals(ShopErrorTypes.ILLEGAL_REQUEST_BODY, shopException.getErrorTypes());
	}

	@Test
	void testAddSpecialOfferForProductWhenProductNotExist() {
		Long productId = 10L;
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setPrice(10L);
		when(productRepository.getProductById(anyLong())).thenReturn(Optional.empty());

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId));

		assertEquals(ShopErrorTypes.PRODUCT_NOT_FOUND, shopException.getErrorTypes());
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

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.addSpecialOfferForProduct(specialOffer, productId));

		assertEquals(ShopErrorTypes.ILLEGAL_REQUEST_BODY, shopException.getErrorTypes());
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
		specialOffer.setStart(getTimeWithAdd(-1));
		specialOffer.setStop(getTimeWithAdd(2));
		specialOffer.setPrice(10L);
		specialOffer.setProduct(product);
		when(specialOfferRepository.getAllByProductIdOrderByPrice(any())).thenReturn(List.of(specialOffer));

		SpecialOffer returnSpecialOffer = specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId);

		assertEquals(specialOffer, returnSpecialOffer);
	}

	@Test
	void testGetCurrentBestSpecialOfferForProductWhenNoSpecialOfferForProduct() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		when(specialOfferRepository.getAllByProductIdOrderByPrice(any())).thenReturn(new ArrayList<>());

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId));

		assertEquals(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND, shopException.getErrorTypes());
		verify(specialOfferRepository, times(1)).getAllByProductIdOrderByPrice(any());
	}

	@Test
	void testGetCurrentBestSpecialOfferForProductWhenExpired() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setStart(getTimeWithAdd(-2));
		specialOffer.setStop(getTimeWithAdd(-1));
		specialOffer.setPrice(10L);
		specialOffer.setProduct(product);
		when(specialOfferRepository.getAllByProductIdOrderByPrice(any())).thenReturn(new ArrayList<>());

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId));

		assertEquals(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND, shopException.getErrorTypes());
		verify(specialOfferRepository, times(1)).getAllByProductIdOrderByPrice(any());
	}

	@Test
	void testGetCurrentBestSpecialOfferForProductWhenMoreThanOneExists() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setStart(getTimeWithAdd(-2));
		specialOffer.setStop(getTimeWithAdd(1));
		specialOffer.setPrice(10L);
		specialOffer.setProduct(product);
		SpecialOffer specialOffer1 = new SpecialOffer();
		specialOffer1.setId(2L);
		specialOffer1.setStart(getTimeWithAdd(-2));
		specialOffer1.setStop(getTimeWithAdd(1));
		specialOffer1.setPrice(9L);
		specialOffer1.setProduct(product);
		SpecialOffer specialOffer2 = new SpecialOffer();
		specialOffer2.setId(3L);
		specialOffer2.setStart(getTimeWithAdd(-2));
		specialOffer2.setStop(getTimeWithAdd(1));
		specialOffer2.setPrice(11L);
		specialOffer2.setProduct(product);
		when(specialOfferRepository.getAllByProductIdOrderByPrice(any())).thenReturn(List.of(specialOffer,specialOffer1, specialOffer2));

		SpecialOffer specialOfferReturned = specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId);

		assertEquals(specialOffer1, specialOfferReturned);
	}

	@Test
	void testGetCurrentBestSpecialOfferForProductWhenBestOfferAreNotTimeAvaliable() {
		Long productId = 1L;
		Product product = new Product();
		product.setId(productId);
		product.setName("Kayak");
		SpecialOffer specialOffer = new SpecialOffer();
		specialOffer.setId(1L);
		specialOffer.setStart(getTimeWithAdd(-2));
		specialOffer.setStop(getTimeWithAdd(1));
		specialOffer.setPrice(1000L);
		specialOffer.setProduct(product);
		SpecialOffer specialOffer1 = new SpecialOffer();
		specialOffer1.setId(2L);
		specialOffer1.setStart(getTimeWithAdd(1));
		specialOffer1.setStop(getTimeWithAdd(2));
		specialOffer1.setPrice(9L);
		specialOffer1.setProduct(product);
		SpecialOffer specialOffer2 = new SpecialOffer();
		specialOffer2.setId(3L);
		specialOffer2.setStart(getTimeWithAdd(-2));
		specialOffer2.setStop(getTimeWithAdd(-1));
		specialOffer2.setPrice(11L);
		specialOffer2.setProduct(product);
		when(specialOfferRepository.getAllByProductIdOrderByPrice(any())).thenReturn(List.of(specialOffer,specialOffer1, specialOffer2));

		SpecialOffer specialOfferReturned = specialOfferUseCase.getCurrentBestSpecialOfferForProduct(productId);

		assertEquals(specialOffer, specialOfferReturned);
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

		ShopException shopException = assertThrows(ShopException.class, () -> specialOfferUseCase.deleteSpecialOffer(specialOfferId));

		assertEquals(ShopErrorTypes.SPECIAL_OFFER_NOT_FOUND, shopException.getErrorTypes());
		verify(specialOfferRepository, times(1)).findById(any());
		verify(specialOfferRepository, times(0)).delete(any());
	}
}
