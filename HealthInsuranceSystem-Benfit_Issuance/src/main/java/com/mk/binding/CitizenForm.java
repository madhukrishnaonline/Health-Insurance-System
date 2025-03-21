package com.mk.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenForm {
	private Long caseNum;
	private String holderName;
	private String planName;
	private String planStatus;
	private Double beneficiaryAmt;
	private Long holderSsn;
	private String bankName;
	private String accountNumber;
}