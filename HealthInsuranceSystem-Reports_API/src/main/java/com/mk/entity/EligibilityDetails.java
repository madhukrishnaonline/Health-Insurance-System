package com.mk.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate planStartDate;

	@Column(name = "PLAN_END_DATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate planEndDate;

	@Column(name = "CREATE_DATE",updatable = false)
	@CreationTimestamp
	private LocalDate createDate;

	@Column(name = "UPDATE_DATE",insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
}// class