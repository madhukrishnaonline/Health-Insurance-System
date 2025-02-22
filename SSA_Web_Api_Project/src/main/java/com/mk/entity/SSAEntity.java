package com.mk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SSA_WEB_API")
public class SSAEntity {

	@Id
	private Integer id;

	@Column(name = "state_name", length = 20)
	private String stateName;

	private Integer ssn;
}//class