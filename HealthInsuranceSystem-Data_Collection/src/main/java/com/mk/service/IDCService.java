package com.mk.service;

import java.util.Map;

import com.mk.dto.CreateChildRequest;
import com.mk.dto.EducationDetails;
import com.mk.dto.IncomeDetails;
import com.mk.dto.PlanSelection;
import com.mk.dto.SummaryScreen;

public interface IDCService {

	Long loadCaseNumber(Integer appId);

	Map<Integer,String> getPlanNames();

	Long savePlanSelection(PlanSelection planSelection);

	Long saveIncomeDetails(IncomeDetails incomeDetails);

	Long saveEducationDetails(EducationDetails educationDetails);

	Long saveKidsDetails(CreateChildRequest childrensDetails);

	SummaryScreen displaySummaryScreen(Long caseNumber);
}
