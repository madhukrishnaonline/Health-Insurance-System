package com.mk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.entity.SSAEntity;
import com.mk.service.SSAService;

@RestController
@RequestMapping("/ssa-web-api")
public class SSAController {

	@Autowired
	private SSAService service;

	@GetMapping("/ssn/{ssn}")
	public ResponseEntity<String> getBySSN(@PathVariable Integer ssn) {
		String data = service.getBySSN(ssn);
		if (!data.isBlank()) {
			return new ResponseEntity<String>(data, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		} //else
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<SSAEntity>> findAllSSNs() {
		List<SSAEntity> listOfSSNs = service.getAllSSNs();
		return ResponseEntity.ok(listOfSSNs);
	}

}//class