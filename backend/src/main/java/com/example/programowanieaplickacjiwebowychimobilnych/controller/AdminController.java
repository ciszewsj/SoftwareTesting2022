package com.example.programowanieaplickacjiwebowychimobilnych.controller;

import com.example.programowanieaplickacjiwebowychimobilnych.config.ApplicationRoles;
import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.Parcel;
import com.example.programowanieaplickacjiwebowychimobilnych.data.request.ChangeParcelStatusRequest;
import com.example.programowanieaplickacjiwebowychimobilnych.usecase.AdminUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin")
@RolesAllowed({ApplicationRoles.ROLE_ADMIN})
@CrossOrigin
public class AdminController {
	private final AdminUseCase adminUseCase;

	@GetMapping("/parcels")
	private Page<Parcel> getAllParcels() {
		return adminUseCase.getParcels();
	}

	@PutMapping("/parcel")
	private void changeParcelStatus(@RequestBody ChangeParcelStatusRequest request) {
		adminUseCase.changeParcelStatus(request.getParcelId(), request.getParcelStatus());
	}

}
