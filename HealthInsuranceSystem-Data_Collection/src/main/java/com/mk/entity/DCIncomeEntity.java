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
@Table(name = "DC_INCOME")
public class DCIncomeEntity {

	@Id
	@Column(name="INCOME_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer incomeId;

	@Column(name="CASE_NUM")
	private Long caseNum;

	@Column(name="EMP_INCOME")
	private Double empIncome;

	@Column(name="PROPERTY_INCOME")
	private Double propertyIncome;
}