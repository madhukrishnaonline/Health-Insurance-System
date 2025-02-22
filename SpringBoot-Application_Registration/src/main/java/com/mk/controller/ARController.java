package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.CitizenApp;
import com.mk.service.CitizenAppServiceImpl;

@RestController
@RequestMapping("/citizen")
public class ARController {

	@Autowired
	private CitizenAppServiceImpl service;

	@PostMapping("/create")
	public ResponseEntity<String> createCitizenApp(@RequestBody CitizenApp citizenApp) {
		Integer id = service.createCitizenApplication(citizenApp);
		if (id > 0) {
			return new ResponseEntity<String>("Citizen Application created With id :: " + id, HttpStatus.CREATED);
		} //if
		else {
			return new ResponseEntity<String>("Not Created", HttpStatus.NOT_ACCEPTABLE);
		} //else
	}//createCitizenApp

}//class