package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.EligibilityDetails;
import com.mk.service.IEDService;

@RestController
@RequestMapping("/eligibility")
public class EDController {

	@Autowired
	private IEDService service;

	@GetMapping("/eligible/{caseNumber}")
	public ResponseEntity<EligibilityDetails> getEligibilityData(@PathVariable Long caseNumber) {
		EligibilityDetails data = service.validateEligibility(caseNumber);
		if (data != null) {
			return new ResponseEntity<EligibilityDetails>(data, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<EligibilityDetails>(data, HttpStatus.NOT_FOUND);
		} //else
	}//getEligibilityData

}//class