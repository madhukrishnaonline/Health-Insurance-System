package com.mk.entity;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer childrenId;
	
	private String childName;

	private Long caseNum;
	
	private Integer age;

	private Long childrenSsn;
}//class