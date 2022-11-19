package com.example.programowanieaplickacjiwebowychimobilnych.data.request;

import com.example.programowanieaplickacjiwebowychimobilnych.data.entity.ParcelStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeParcelStatusRequest {
	@NotBlank
	private Long parcelId;
	@NotBlank
	private ParcelStatus parcelStatus;
}
