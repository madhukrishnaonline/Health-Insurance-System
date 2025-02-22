package com.mk.dto;

import lombok.Data;

@Data
public class ActivateAccount {
	private String email;
	private String tempPswd;
	private String newPswd;
	private String cnfPswd;
}