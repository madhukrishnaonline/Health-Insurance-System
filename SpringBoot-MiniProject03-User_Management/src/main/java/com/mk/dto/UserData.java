package com.mk.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserData {
	private Integer id;
	
	private String name;

	private Long mobile;

	private String email;

	private String gender;

	private LocalDate dob;

	private Integer ssn;
	
	private String status;
}//class