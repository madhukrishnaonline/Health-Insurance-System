package com.mk.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	private Integer id;
	
	private String name;

	private Long mobile;

	private String email;

	private String gender;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;

	private Integer ssn;
	
	private String status;
}//class