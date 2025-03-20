package com.mk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.constants.AppConstants;
import com.mk.entity.Plans;
import com.mk.entity.PlansCategory;
import com.mk.properties.AppProperties;
import com.mk.sevice.IPlanService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private IPlanService planService;

	Map<String, String> messages = new HashMap<>();

	public AdminController(IPlanService planService, AppProperties properties) {
		this.planService = planService;
		this.messages = properties.getMessages();
	}
	
	@PostMapping("/register/category")
	public ResponseEntity<String> registerCategory(@RequestBody PlansCategory category){
		Boolean response = planService.registerCategory(category);
		if(response) {
			return new ResponseEntity<String>("Registered", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);			
		}
	}

	@PostMapping("/register/plan")
	public ResponseEntity<String> registerPlanDetails(@RequestBody Plans plans) {
		Boolean planDetails = planService.registerPlanDetails(plans);
		String msg = AppConstants.EMPT_STR;
		if (planDetails) {
			msg = messages.get(AppConstants.PLAN_REG_SUCC);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} // if
		else {
			msg = messages.get(AppConstants.PLAN_REG_FAIL);
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		} // else
	}

	@GetMapping("/getCategories")
	public ResponseEntity<Map<Integer, String>> getPlanCategories() {
		Map<Integer, String> categoryDetails = planService.getCategoryDetails();
		return new ResponseEntity<Map<Integer, String>>(categoryDetails, HttpStatus.OK);
	}

	@GetMapping("/planDetails")
	public ResponseEntity<List<Plans>> getPlanDetails() {
		List<Plans> allPlansDetails = planService.getAllPlansDetails();
		if (allPlansDetails != null) {
			return new ResponseEntity<List<Plans>>(allPlansDetails, HttpStatus.OK);
		} // if
		else {
			return new ResponseEntity<List<Plans>>(allPlansDetails, HttpStatus.NO_CONTENT);
		} // else
	}

	@GetMapping("/plan/{id}")
	public ResponseEntity<Plans> getPlanDetailsById(@PathVariable Integer id) {
		Plans planDetailsById = planService.getPlanDetailsById(id);
		if (planDetailsById != null) {
			return new ResponseEntity<Plans>(planDetailsById, HttpStatus.OK);
		} // if
		else {
			return new ResponseEntity<Plans>(planDetailsById, HttpStatus.NOT_FOUND);
		} // else
	}

	@PutMapping("/update/Details")
	public ResponseEntity<String> updatePlanDetails(@RequestBody Plans plans) {
		Boolean planDetailsById = planService.updatePlanDetails(plans);
		String msg = AppConstants.EMPT_STR;
		if (planDetailsById) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} // if
		else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
			return new ResponseEntity<String>(msg, HttpStatus.NOT_MODIFIED);
		} // else
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer id) {
		Boolean deletePlanDetailsById = planService.deletePlanDetailsById(id);
		String msg = AppConstants.EMPT_STR;
		if (deletePlanDetailsById) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
			return new ResponseEntity<String>(msg, HttpStatus.GONE);
		} // if
		else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
			return new ResponseEntity<String>(msg, HttpStatus.NOT_MODIFIED);
		} // else
	}

}// class