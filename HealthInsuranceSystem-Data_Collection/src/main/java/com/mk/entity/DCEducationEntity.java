package com.mk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DC_EDUCATION")
public class DCEducationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer eduId;
	
	private Long caseNum;

	private String highestQualification;

	private Integer graduationYear;
	
	private String universityName;
}//class