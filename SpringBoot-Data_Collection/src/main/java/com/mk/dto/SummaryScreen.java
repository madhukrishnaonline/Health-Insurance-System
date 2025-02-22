package com.mk.dto;

import java.util.List;

import lombok.Data;

@Data
public class SummaryScreen {

	private List<ChildrensDetails> childrensDetails;

	private EducationDetails educationDetails;

	private IncomeDetails incomeDetails;

	private String planName;

}//class