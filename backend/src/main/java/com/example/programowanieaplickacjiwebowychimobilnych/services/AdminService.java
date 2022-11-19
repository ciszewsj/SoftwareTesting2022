package com.example.programowanieaplickacjiwebowychimobilnych.services;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Parcel;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.ParcelStatus;
import com.example.programowanieaplickacjiwebowychimobilnych.repositories.ParcelRepository;
import com.example.programowanieaplickacjiwebowychimobilnych.usecase.AdminUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminService implements AdminUseCase {
	private final ParcelRepository parcelRepository;

	@Override
	public Page<Parcel> getParcels() {
		Pageable pageRequest = PageRequest.of(1, 12);
		return parcelRepository.findAll(pageRequest);
	}

	@Override
	public Parcel getParcel(Long parcelId) {
		return null;
	}

	@Override
	public void changeParcelStatus(Long parcelId, ParcelStatus parcelStatus) {

	}
}
