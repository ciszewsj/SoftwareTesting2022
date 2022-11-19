package com.example.programowanieaplickacjiwebowychimobilnych.usecase;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Address;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Customer;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Parcel;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Recipient;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.CreateParcelRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ParcelUseCase {

	Parcel createParcel(Long userId,CreateParcelRequest request);

	Parcel getParcel(Long parcelId, Long userId);

	List<Parcel> getParcels(Long userId);

}
