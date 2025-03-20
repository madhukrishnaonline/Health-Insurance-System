package com.mk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CO_TRIGGERs")
public class CoTriggerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer triggerId;

	private Long caseNum;

	private byte[] coPdf;

	private String trStatus;
}//class