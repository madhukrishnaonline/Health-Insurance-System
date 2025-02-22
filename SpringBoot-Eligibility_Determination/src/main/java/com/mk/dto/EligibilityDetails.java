package com.mk.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityDetails {
	private String planName;

	private String planStatus;

	private Double beneficiaryAmt;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private String denialReason;
}//class