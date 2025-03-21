package com.mk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.dto.ChildrensDetails;
import com.mk.dto.CreateChildRequest;
import com.mk.dto.EducationDetails;
import com.mk.dto.IncomeDetails;
import com.mk.dto.PlanSelection;
import com.mk.dto.SummaryScreen;
import com.mk.entity.CitizenAppEntity;
import com.mk.entity.DCCasesEntity;
import com.mk.entity.DCChildrensEntity;
import com.mk.entity.DCEducationEntity;
import com.mk.entity.DCIncomeEntity;
import com.mk.entity.PlanEntity;
import com.mk.repository.CitizenAppRepository;
import com.mk.repository.DCCasesRepository;
import com.mk.repository.DCChildrensRepository;
import com.mk.repository.DCEducationRepository;
import com.mk.repository.DCIncomeRepository;
import com.mk.repository.PlanRepository;

@Service
public class DCServiceImpl implements IDCService {

	@Autowired
	private DCCasesRepository casesRepository;

	@Autowired
	private DCIncomeRepository incomeRepository;

	@Autowired
	private DCEducationRepository educationRepository;

	@Autowired
	private DCChildrensRepository childrensRepository;

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private CitizenAppRepository citizenAppRepository;

	@Override
	public Long loadCaseNumber(Integer appId) {
		Optional<CitizenAppEntity> byId = citizenAppRepository.findById(appId);
		Optional<DCCasesEntity> byId2 = casesRepository.findById(appId);
		if (byId.isPresent()) {
			DCCasesEntity entity = new DCCasesEntity();
			if (byId2.isEmpty()) {
				entity.setAppId(appId);
				entity = casesRepository.save(entity);
				return entity.getCaseNum();
			} //inner if
			return byId2.get().getCaseNum();
		} //if
		return null;
	}//loadCaseNumber

	@Override
	public Map<Integer, String> getPlanNames() {
		List<PlanEntity> all = planRepository.findAll();
		Map<Integer, String> map = new HashMap<>();
		all.forEach(data -> {
			map.put(data.getPlanId(), data.getPlanName());
		});
		return map;
	}//getPlanNames

	@Override
	public Long savePlanSelection(PlanSelection planSelection) {
		Optional<DCCasesEntity> entity = casesRepository.findById(planSelection.getCaseNum());
		if (entity.isPresent()) {
			DCCasesEntity casesEntity = entity.get();
			casesEntity.setPlanId(planSelection.getPlanId());
			DCCasesEntity data = casesRepository.save(casesEntity);

			return data.getCaseNum();
		} //if
		return null;
	}//savePlanSelection

	@Override
	public Long saveIncomeDetails(IncomeDetails incomeDetails) {
		Optional<DCCasesEntity> entity = casesRepository.findById(incomeDetails.getCaseNum());
		if (entity.isPresent()) {
			DCIncomeEntity entity1 = new DCIncomeEntity();
			BeanUtils.copyProperties(incomeDetails, entity1);

			DCIncomeEntity save = incomeRepository.save(entity1);

			return save.getCaseNum();
		} //if
		return 0l;
	}//saveIncomeDetails

	@Override
	public Long saveEducationDetails(EducationDetails educationDetails) {
		Optional<DCCasesEntity> entity = casesRepository.findById(educationDetails.getCaseNum());
		if (entity.isPresent()) {
			DCEducationEntity entity1 = new DCEducationEntity();
			BeanUtils.copyProperties(educationDetails, entity1);
			DCEducationEntity data = educationRepository.save(entity1);

			return data.getCaseNum();
		} //if
		return 0l;
	}//saveEducationDetails

	@Override
	public Long saveKidsDetails(CreateChildRequest childrensDetails) {
		Optional<DCCasesEntity> entity = casesRepository.findById(childrensDetails.getCaseNum());
		if (entity.isPresent()) {
			childrensDetails.getChildrenDetails().forEach(child -> {
				DCChildrensEntity entity1 = new DCChildrensEntity();
				BeanUtils.copyProperties(child, entity1);
				entity1.setCaseNum(childrensDetails.getCaseNum());
				childrensRepository.save(entity1);
			});
			return childrensDetails.getCaseNum();
		} //if
		return 0l;
	}//saveKidsDetails

	@Override
	public SummaryScreen displaySummaryScreen(Long caseNumber) {
		SummaryScreen summary = new SummaryScreen();

		DCIncomeEntity incomeEntity = incomeRepository.findByCaseNum(caseNumber);
		IncomeDetails incomeDetails = new IncomeDetails();
		BeanUtils.copyProperties(incomeEntity, incomeDetails);
		summary.setIncomeDetails(incomeDetails);

		List<DCChildrensEntity> childrenEntity = childrensRepository.findByCaseNum(caseNumber);
		List<ChildrensDetails> childrensDetails = new ArrayList<>();
		childrenEntity.forEach(child -> {
			ChildrensDetails details = new ChildrensDetails();
			BeanUtils.copyProperties(child, details);
			childrensDetails.add(details);
		});
		summary.setChildrensDetails(childrensDetails);

		DCEducationEntity educationEntity = educationRepository.findByCaseNum(caseNumber);
		EducationDetails educationDetails = new EducationDetails();
		BeanUtils.copyProperties(educationEntity, educationDetails);
		summary.setEducationDetails(educationDetails);

		DCCasesEntity entity = casesRepository.findByCaseNum(caseNumber);
		String planName = "";
		if (entity != null) {
			Integer planId = entity.getPlanId();
			Optional<PlanEntity> byId = planRepository.findById(planId);
			if (byId.isPresent()) {
				planName = byId.get().getPlanName();
			} //if
		} //if
		summary.setPlanName(planName);

		return summary;
	}//displaySummaryScreen

}//class