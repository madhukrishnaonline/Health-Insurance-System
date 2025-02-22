package com.mk.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateChildRequest {
	private Long caseNum;
	private List<ChildrensDetails> childrenDetails;
}
