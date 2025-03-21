package com.mk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CO_TRIGGERS")
public class CoTriggerEntity {

	@Id
	@Column(name="TRIGGER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer triggerId;

	@Column(name="CASE_NUM")
	private Long caseNum;

	@Lob
	@Column(name="CO_PDF")
	private byte[] coPdf;

	@Column(name="TR_STATUS")
	private String trStatus;
}//class