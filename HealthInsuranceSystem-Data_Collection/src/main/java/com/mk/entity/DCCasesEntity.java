package com.mk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DC_CASES")
public class DCCasesEntity {

	@Id
	@Column(name="CASE_NUM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long caseNum;

	@Column(name="APP_ID")
	private Integer appId;

	@Column(name="PLAN_ID")
	private Integer planId;
}//class