package com.example.programowanieaplickacjiwebowychimobilnych.controller;

import com.example.programowanieaplickacjiwebowychimobilnych.config.ApplicationRoles;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Customer;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Parcel;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.CreateParcelRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.usecase.ParcelUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/parcel")
@RequiredArgsConstructor
@Slf4j
//@RolesAllowed({ApplicationRoles.ROLE_ADMIN, ApplicationRoles.ROLE_User})
@CrossOrigin
public class ParcelController {
	private final ParcelUseCase parcelUseCase;

	@PostMapping
	public Parcel createParcel(
//			@AuthenticationPrincipal Customer customer,
			@RequestBody @Validated CreateParcelRequest request) {
//		log.info(customer.toString());
		return parcelUseCase.createParcel(1L, request);
	}

	@GetMapping("/{parcelId}")
	public Parcel getParcel(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long parcelId) {
		return parcelUseCase.getParcel(parcelId, ((Customer) userDetails).getId());
	}

	@GetMapping
	public List<Parcel> getUserParcels(@AuthenticationPrincipal UserDetails userDetails) {
		Customer customer = (Customer) userDetails;
		return parcelUseCase.getParcels(customer.getId());
	}
}
