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

import lombok.Data;

@Data
@Entity
@Table(name = "PLANS_CATEGORY")
@SQLDelete(sql = "UPDATE PLANS_CATEGORY SET STATUS='INACTIVE' WHERE CATEGORY_ID=?")
@SQLRestriction(value = "STATUS <> 'INACTIVE'")
public class PlansCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_TIME",updatable = false)
	@CreationTimestamp
	private LocalDate createdTime;

	@Column(name = "UPDATED_TIME",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedTime;

	@Column(name = "STATUS")
	private String status = "ACTIVE";

}// CLASS