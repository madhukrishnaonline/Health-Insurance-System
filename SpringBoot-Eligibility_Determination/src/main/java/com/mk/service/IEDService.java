package com.mk.service;

import com.mk.dto.EligibilityDetails;

public interface IEDService {

	EligibilityDetails validateEligibility(Long caseNumber);
	
}//interface