package com.mk.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ELIGIBILITY_DATA")
public class EligibilityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer elgibilityId;
	
	private Long caseNum;

	private String holderName;

	private String planName;
	
	private String planStatus;

	private Double beneficiaryAmt;

	private Long holderSsn;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private String denialReason;	
}//class