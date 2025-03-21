package com.mk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivateAccount {
	private String email;
	private String tempPswd;
	private String newPswd;
	private String cnfPswd;
}