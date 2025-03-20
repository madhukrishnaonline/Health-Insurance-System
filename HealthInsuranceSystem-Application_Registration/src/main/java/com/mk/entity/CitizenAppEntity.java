package com.mk.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_APP")
public class CitizenAppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer app_id;
	
	@Column(length = 25)
	private String fullname;
	
	@Column(length = 25)
	private String email;
	
	private LocalDate dob;
	
	@Column(length = 10)
	private Long phone;
	
	private Integer ssn;
	
	@Column(length = 10)
	private String gender;
	
	@Column(length = 15)
	private String stateName;
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE")
	private LocalDate createdate;
	
	@UpdateTimestamp
	@Column(name = "UPDATE_DATE")
	private LocalDate updatedate;
	
	@Column(length = 25)
	private String createdBy;
	
	@Column(length = 25)
	private String updatedBy;

}
