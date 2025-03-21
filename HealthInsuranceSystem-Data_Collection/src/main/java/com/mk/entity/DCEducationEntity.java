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
@Table(name = "DC_EDUCATION")
public class DCEducationEntity {

	@Id
	@Column(name="EDU_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer eduId;
	
	@Column(name="CASE_NUM")
	private Long caseNum;

	@Column(name="HIGHEST_QUALIFICATION")
	private String highestQualification;

	@Column(name="GRADUATION_YEAR")
	private Integer graduationYear;
	
	@Column(name="UNIVERSITY_NAME")
	private String universityName;
}//class