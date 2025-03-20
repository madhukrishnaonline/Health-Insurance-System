package com.mk.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportsRequest {
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
}//class