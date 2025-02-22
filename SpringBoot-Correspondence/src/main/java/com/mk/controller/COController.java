package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.CoTriggerStatusResponse;
import com.mk.service.ICOService;

@RestController
public class COController {

	@Autowired
	private ICOService service;

	@GetMapping("/send/notice")
	public ResponseEntity<CoTriggerStatusResponse> sendNoticesToCitizens() {
		CoTriggerStatusResponse response = service.sendEligibilityNoticeToCitizen();
		return new ResponseEntity<CoTriggerStatusResponse>(response, HttpStatus.OK);
	}
}//class