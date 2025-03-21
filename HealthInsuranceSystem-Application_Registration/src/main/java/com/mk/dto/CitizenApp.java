package com.mk.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CitizenApp {
	private String fullname;
	private String email;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;
	private Long phone;
	private Integer ssn;
	private String gender;
}//class