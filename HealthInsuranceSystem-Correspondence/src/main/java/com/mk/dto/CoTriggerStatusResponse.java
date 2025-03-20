package com.mk.dto;

import lombok.Data;

@Data
public class CoTriggerStatusResponse {
	private Integer totalTriggers;
	private Integer successTriggers;
	private Integer failedTriggers;
}
