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
@Table(name = "USER_MANAGEMENT")
//@SQLDelete(sql = "UPDATE USER_MANAGEMENT SET STATUS='INACTIVE' WHERE ID=?")
//@SQLRestriction(value = "STATUS <> 'INACTIVE'")
public class UserManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 25)
	private String name;

	@Column(length = 10)
	private Long mobile;

	@Column(length = 30,unique = true)
	private String email;
	
	@Column(length = 70)
	private String password;

	@Column(length = 10)
	private String gender;

	@Column(name = "DATE_OF_BIRTH")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;

	private Integer ssn;
	
	@Column(length = 8)
	private String status;

	@CreationTimestamp
	@Column(name = "CREATED_DATE",updatable = false)
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE",insertable = false)
	private LocalDate updatedDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

}// class