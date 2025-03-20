package com.mk.entity;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long caseNum;

	private Integer appId;

	private Integer planId;
}//class