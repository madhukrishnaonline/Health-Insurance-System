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
@Table(name = "DC_CHILDRENS")
public class DCChildrensEntity {

	@Id
	@Column(name="CHILDREN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer childrenId;
	
	@Column(name="CHILD_NAME")
	private String childName;

	@Column(name="CASE_NUM")
	private Long caseNum;
	
	private Integer age;

	@Column(name="CHILDREN_SSN")
	private Long childrenSsn;
}//class