package com.example.programowanieaplickacjiwebowychimobilnych.services;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.*;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.CreateParcelRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.exception.ParametrizedException;
import com.example.programowanieaplickacjiwebowychimobilnych.repositories.*;
import com.example.programowanieaplickacjiwebowychimobilnych.usecase.ParcelUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ParcelService implements ParcelUseCase {
	private final ParcelRepository parcelRepository;
	private final ParcelStatusRepository parcelStatusRepository;
	private final AddressRepository addressRepository;
	private final RecipientRepository recipientRepository;
	private final CustomerRepository customerRepository;

	@Override
	public Parcel createParcel(Long userId, CreateParcelRequest request) {
		Customer customer = customerRepository.findById(userId).orElseThrow(new ParametrizedException("User not found"));

		Address recipientAddress = new Address();
		recipientAddress.setPostCode(request.getPostCode());
		recipientAddress.setStreet(request.getStreet());
		recipientAddress.setHouseNumber(request.getHouseNumber());
		recipientAddress.setCity(request.getCity());
		recipientAddress = addressRepository.save(recipientAddress);

		Recipient recipient = new Recipient();
		recipient.setEmail(request.getEmail());
		recipient.setName(request.getName());
		recipient = recipientRepository.save(recipient);


		ParcelStatus newParcelStatus = new ParcelStatus(customer.getAddress());
		newParcelStatus = parcelStatusRepository.save(newParcelStatus);

		Parcel newParcel = new Parcel(newParcelStatus);
		newParcel.setRecipient(recipient);
		newParcel.setSender(customer);
		newParcel.setAddress(recipientAddress);
		return parcelRepository.save(newParcel);
	}


	@Override
	public Parcel getParcel(Long parcelId, Long userId) {
		return parcelRepository.findByIdAndSenderId(parcelId, userId).orElseThrow(new ParametrizedException("Not find parcel"));
	}


	@Override
	public List<Parcel> getParcels(Long userId) {
		return parcelRepository.findBySenderId(userId, Pageable.ofSize(12)).getContent();
	}
}
