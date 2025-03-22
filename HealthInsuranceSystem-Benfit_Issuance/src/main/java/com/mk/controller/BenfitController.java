package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.service.IBIService;

@RestController
@RequestMapping("/benfit")
public class BenfitController {

	@Autowired
	private IBIService service;

	@GetMapping("/process")
	public ResponseEntity<String> processApprovedCitizens() {
		service.createCSVOfApprovedCitizens();
		return ResponseEntity.ok("Process Completed");
	}
}