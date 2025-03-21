package com.mk.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@Column(name="APP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer appId;
	
	@Column(length = 25)
	private String fullname;
	
	@Column(length = 25)
	private String email;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;
	
	@Column(length = 10)
	private Long phone;
	
	private Long ssn;
	
	@Column(length = 10)
	private String gender;
	
	@Column(name="STATE_NAME",length = 15)
	private String stateName;
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createdate;
	
	@UpdateTimestamp
	@Column(name = "UPDATE_DATE",insertable = false)
	private LocalDate updatedate;
	
	@Column(name="CREATED_BY",length = 25)
	private String createdBy;
	
	@Column(name="UPDATED_BY",length = 25)
	private String updatedBy;
}//class