package com.mk.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	@Id
	@Column(name = "ELIGIBLE_ID")
	private Integer id;

	@Column(length = 25)
	private String name;

	@Column(length = 10)
	private Long mobile;

	@Column(length = 30)
	private String email;

	@Column(length = 10)
	private String gender;

	private Integer ssn;

	@Column(name = "PLAN_NAME", length = 20)
	private String planName;
	
	@Column(name = "PLAN_STATUS",length = 10)
	private String planStatus;

	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;

	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;

	@Column(name = "CREATE_DATE")
	private LocalDate createDate;

	@Column(name = "UPDATE_DATE")
	private LocalDate updateDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
}// class