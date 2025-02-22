package com.mk.service;

import java.util.List;

import com.mk.dto.ReportsRequest;
import com.mk.dto.ReportsResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface IReportsService {
	
	List<String> getDistinctPlans();

	List<String> getDistinctStatus();
	
	List<ReportsResponse> search(ReportsRequest request);
	
	void generateExcelReport(HttpServletResponse response)throws Exception;
	
	void generatePDFReport(HttpServletResponse response)throws Exception;
	
}
