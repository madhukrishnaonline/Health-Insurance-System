package com.mk.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import lombok.Data;

@Entity
@Table(name = "PLANS_CATEGORY")
@Data
@SQLDelete(sql = "UPDATE PLANS_CATEGORY SET STATUS='INACTIVE' WHERE CATEGORY_ID=?")
@SQLRestriction(value = "STATUS <> 'INACTIVE'")
public class PlansCategory {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Integer category_id;

	@Column(name = "CATEGORY_NAME")
	private String category_name;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_TIME")
	private LocalDate createdTime;

	@Column(name = "UPDATED_TIME")
	private LocalDate updatedTime;

	@Column(name = "STATUS")
	private String status = "ACTIVE";

}// CLASS