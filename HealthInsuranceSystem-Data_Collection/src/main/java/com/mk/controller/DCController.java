package com.mk.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.CreateCaseResponse;
import com.mk.dto.CreateChildRequest;
import com.mk.dto.EducationDetails;
import com.mk.dto.IncomeDetails;
import com.mk.dto.PlanSelection;
import com.mk.dto.SummaryScreen;
import com.mk.service.IDCService;

@RestController
public class DCController {

	@Autowired
	private IDCService service;

	@GetMapping("/search/{appId}")
	public ResponseEntity<CreateCaseResponse> loadCaseNumber(@PathVariable Integer appId) {
		Long caseNumber = service.loadCaseNumber(appId);
		Map<Integer, String> planNames = service.getPlanNames();

		CreateCaseResponse response = new CreateCaseResponse();
		response.setCaseNum(caseNumber);
		response.setPlanName(planNames);

		if (caseNumber != 0) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} //else
	}//loadCaseNumber

	/*	@GetMapping("/plans")
		public ResponseEntity<List<String>> getPlanNames() {
			List<String> planNames = service.getPlanNames();
			if (planNames != null) {
				return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);
			} //if
			else {
				return new ResponseEntity<List<String>>(planNames, HttpStatus.NO_CONTENT);
			} //else
		}//getPlanNames
	*/

	@PostMapping("/savePlan")
	public ResponseEntity<Long> savePlanSelectionData(@RequestBody PlanSelection planSelection) {
		Long caseNumber = service.savePlanSelection(planSelection);
		if (caseNumber != 0) {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.CREATED);
		} //if
		else {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.INTERNAL_SERVER_ERROR);
		} //else
	}//savePlanSelectionData

	@PostMapping("/saveIncome")
	public ResponseEntity<Long> saveIncomeDetailsData(@RequestBody IncomeDetails incomeDetails) {
		Long caseNumber = service.saveIncomeDetails(incomeDetails);
		if (caseNumber != 0) {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.CREATED);
		} //if
		else {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.INTERNAL_SERVER_ERROR);
		} //else
	}//saveIncomeDetailsData

	@PostMapping("/saveEducation")
	public ResponseEntity<Long> saveEducationDetailsData(@RequestBody EducationDetails educationDetails) {
		Long caseNumber = service.saveEducationDetails(educationDetails);
		if (caseNumber != 0) {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.CREATED);
		} //if
		else {
			return new ResponseEntity<Long>(caseNumber, HttpStatus.INTERNAL_SERVER_ERROR);
		} //else
	}//saveEducationDetailsData

	@PostMapping("/saveChildrens")
	public ResponseEntity<SummaryScreen> saveKidsDetailsData(@RequestBody CreateChildRequest childrensDetails) {
		Long caseNumber = service.saveKidsDetails(childrensDetails);
		
		SummaryScreen summaryScreen = service.displaySummaryScreen(caseNumber);
		
		if (caseNumber != 0) {
			return new ResponseEntity<SummaryScreen>(summaryScreen, HttpStatus.CREATED);
		} //if
		else {
			return new ResponseEntity<SummaryScreen>(summaryScreen, HttpStatus.INTERNAL_SERVER_ERROR);
		} //else
	}//saveKidsDetailsData
	
	@GetMapping("/summary/{caseNumber}")
	public ResponseEntity<SummaryScreen> displaySummary(@PathVariable Long caseNumber)
	{
		SummaryScreen summary = service.displaySummaryScreen(caseNumber);
		return new ResponseEntity<SummaryScreen>(summary, HttpStatus.OK);
	}

}//class