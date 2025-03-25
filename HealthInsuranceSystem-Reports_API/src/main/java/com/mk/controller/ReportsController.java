package com.mk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.ReportsRequest;
import com.mk.dto.ReportsResponse;
import com.mk.service.IReportsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private IReportsService reportsService;

	@GetMapping("/plan/details")
	public ResponseEntity<List<String>> getDistinctPlanDetails() {
		List<String> plans = reportsService.getDistinctPlans();
		return new ResponseEntity<List<String>>(plans, HttpStatus.OK);
	}

	@GetMapping("/status/info")
	public ResponseEntity<List<String>> getDistinctStatusInfo() {
		List<String> status = reportsService.getDistinctStatus();
		return new ResponseEntity<List<String>>(status, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<ReportsResponse>> searchRequest(@RequestBody ReportsRequest request) {
		List<ReportsResponse> search = reportsService.search(request);
		return new ResponseEntity<List<ReportsResponse>>(search, HttpStatus.OK);
	}

	@GetMapping("/excelReport")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=report-api.xls";

		response.setHeader(headerKey, headerValue);

		reportsService.generateExcelReport(response);
	}

	@GetMapping("/pdfReport")
	public void generatePDFReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=report-api.pdf";

		response.setHeader(headerKey, headerValue);

		reportsService.generatePDFReport(response);
	}

}//class