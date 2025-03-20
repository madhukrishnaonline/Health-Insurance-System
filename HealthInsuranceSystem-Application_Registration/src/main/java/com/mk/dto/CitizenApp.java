package com.mk.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApp {

	private String fullname;
	private String email;
	private LocalDate dob;
	private Long phone;
	private Integer ssn;
	private String gender;
}//class