package com.mk.sevice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.entity.Plans;
import com.mk.entity.PlansCategory;
import com.mk.repository.PlanCategoryRepository;
import com.mk.repository.PlanRepository;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanCategoryRepository categoryRepository;

	@Override
	public Boolean registerCategory(PlansCategory category) {
//		category.setCreatedTime(LocalDate.now());
		PlansCategory category2 = categoryRepository.save(category);
		return category2.getCategoryId() != null;
	}

	@Override
	public Map<Integer, String> getCategoryDetails() {
		List<PlansCategory> all = categoryRepository.findAll();

		Map<Integer, String> map = new HashMap<>();

		all.forEach(category -> {
			map.put(category.getCategoryId(), category.getCategoryName());
		});
		return map;
	}

	@Override
	public Boolean registerPlanDetails(Plans plans) {
//		plans.setCreatedTime(LocalDate.now());
		Plans save = planRepository.save(plans);
		return save.getPlanId() != null;
	}

	@Override
	public List<Plans> getAllPlansDetails() {
		return planRepository.findAll();
	}

	@Override
	public Plans getPlanDetailsById(Integer id) {
		Optional<Plans> byId = planRepository.findById(id);
		return byId.isPresent() ? byId.get() : byId.orElseThrow();
	}

	@Override
	public Boolean updatePlanDetails(Plans plans) {
		plans.setUpdatedTime(LocalDate.now());
		Plans save = planRepository.save(plans);
		return save.getPlanId() != null ? true : false;
	}

	@Override
	public Boolean deletePlanDetailsById(Integer id) {
		planRepository.deleteById(id);
		return true;
	}
}//class