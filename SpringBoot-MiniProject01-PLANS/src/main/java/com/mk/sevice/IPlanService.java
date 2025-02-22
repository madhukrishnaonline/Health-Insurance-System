package com.mk.sevice;

import java.util.List;
import java.util.Map;

import com.mk.entity.Plans;

public interface IPlanService {
	Map<Integer, String> getCategoryDetails();

	Boolean registerPlanDetails(Plans plans);
	
	List<Plans> getAllPlansDetails();
	
	Plans getPlanDetailsById(Integer id);
	
	Boolean updatePlanDetails(Plans plans);
	
	Boolean deletePlanDetailsById(Integer id);
}
