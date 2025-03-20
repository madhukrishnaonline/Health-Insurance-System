package com.mk.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "PLAN_MASTER")
@SQLDelete(sql = "UPDATE PLAN_MASTER SET STATUS='INACTIVE' WHERE PLAN_ID=?")
@SQLRestriction(value = "STATUS <> 'INACTIVE'")
public class Plans {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="PLAN_ID")
	private Integer planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_START_DATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate planStartDate;
	
	@Column(name="PLAN_END_DATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate planEndDate;
	
	@Column(name="PLAN_CATEGORY_ID")
	private Integer categoryId;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="CREATED_TIME",updatable = false)
	@CreationTimestamp
	private LocalDate createdTime;
	
	@Column(name="UPDATED_TIME",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedTime;
	
	@Column(name="STATUS")
	private String status = "ACTIVE";
}//class