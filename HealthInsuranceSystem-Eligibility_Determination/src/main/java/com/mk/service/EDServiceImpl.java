package com.mk.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.dto.EligibilityDetails;
import com.mk.entity.CitizenAppEntity;
import com.mk.entity.CoTriggerEntity;
import com.mk.entity.DCCasesEntity;
import com.mk.entity.DCChildrensEntity;
import com.mk.entity.DCEducationEntity;
import com.mk.entity.DCIncomeEntity;
import com.mk.entity.EligibilityEntity;
import com.mk.entity.PlanEntity;
import com.mk.repository.CitizenAppRepository;
import com.mk.repository.CoTriggerRepository;
import com.mk.repository.DCCasesRepository;
import com.mk.repository.DCChildrensRepository;
import com.mk.repository.DCEducationRepository;
import com.mk.repository.DCIncomeRepository;
import com.mk.repository.EligibilityEntityRepo;
import com.mk.repository.PlanRepository;

@Service
public class EDServiceImpl implements IEDService {

	@Autowired
	private EligibilityEntityRepo eligibilityEntityRepo;

	@Autowired
	private DCIncomeRepository incomeRepository;

	@Autowired
	private DCEducationRepository educationRepository;

	@Autowired
	private DCChildrensRepository childrensRepository;

	@Autowired
	private CitizenAppRepository citizenAppRepository;

	@Autowired
	private DCCasesRepository casesRepository;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private CoTriggerRepository triggerRepository;

	@Override
	public EligibilityDetails validateEligibility(Long caseNumber) {

		Optional<DCCasesEntity> casesEntity = casesRepository.findById(caseNumber);
		Integer planId = null;
		String planName = null;
		Integer appId = null;

		if (casesEntity.isPresent()) {
			DCCasesEntity dcCasesEntity = casesEntity.get();
			planId = dcCasesEntity.getPlanId();
			appId = dcCasesEntity.getAppId();
		} //if

		Optional<PlanEntity> planEntity = planRepository.findById(planId);
		if (planEntity.isPresent()) {
			planName = planEntity.get().getPlanName();
		} //if

		
		CitizenAppEntity citizenEntity = citizenAppRepository.findById(appId).get();
		LocalDate dob = citizenEntity.getDob();
		LocalDate now = LocalDate.now();
		int age = Period.between(dob, now).getYears();
		
		EligibilityDetails determineElgble = determineEligibility(caseNumber, planName,age);
		
		EligibilityEntity entity = new EligibilityEntity();
		BeanUtils.copyProperties(determineElgble, entity);
		
		entity.setHolderName(citizenEntity.getFullname());
		entity.setHolderMail(citizenEntity.getEmail());
		entity.setMobile(citizenEntity.getPhone());
		entity.setGender(citizenEntity.getGender());
		entity.setCaseNum(caseNumber);
		entity.setHolderSsn(citizenEntity.getSsn());
		
		eligibilityEntityRepo.save(entity);
		
		CoTriggerEntity triggerEntity = new CoTriggerEntity();
		triggerEntity.setCaseNum(caseNumber);
		triggerEntity.setCoPdf(null);
		triggerEntity.setTrStatus("Pending");
		
		triggerRepository.save(triggerEntity);
		
		return determineElgble;
	}//validateSNAPEligibility

	private EligibilityDetails determineEligibility(Long caseNumber, String planName,Integer age) {

		EligibilityDetails eligibilityDetails = new EligibilityDetails();
		eligibilityDetails.setPlanName(planName);

		Optional<DCIncomeEntity> incomeEntity = incomeRepository.findByCaseNum(caseNumber);
		if (planName.equals("SNAP")) {
			if (incomeEntity.isPresent()) {
				Double empIncome = incomeEntity.get().getEmpIncome();
				if (empIncome <= 300) {
					eligibilityDetails.setPlanStatus("Approved");
					eligibilityDetails.setBeneficiaryAmt(350.00);
				} //if
				else {
					eligibilityDetails.setDenialReason("Employee Income is Greater than 300$");
					eligibilityDetails.setPlanStatus("Denied");
				} //else
			} //if
		} //if
		else if (planName.equals("CCAP")) {
			if (incomeEntity.isPresent()) {
				Double empIncome = incomeEntity.get().getEmpIncome();
				List<DCChildrensEntity> childrensEntity = childrensRepository.findByCaseNum(caseNumber);
				if (childrensEntity.size() != 0) {
					long count = childrensEntity.stream().count();
					Integer childAge = null;
					for (DCChildrensEntity child : childrensEntity) {
						childAge = child.getAge();
					} //for
					if (empIncome <= 300) {
						if (count > 0) {
							if (childAge <= 16) {
								eligibilityDetails.setPlanStatus("Approved");
								eligibilityDetails.setBeneficiaryAmt(250.00);
							} //if
							else {
								eligibilityDetails.setDenialReason("Childrens Age greater than 16");
								eligibilityDetails.setPlanStatus("Denied");
							} //else
						} //if
						else {
							eligibilityDetails.setPlanStatus("Denied");
							eligibilityDetails.setDenialReason("Childrens Not Available");
						} //else
					} //if
					else {
						eligibilityDetails.setDenialReason("Employee Income is Greater than 300$");
						eligibilityDetails.setPlanStatus("Denied");
					} //else
				}
			} //if
		} //else if
		else if (planName.equals("Medicaid")) {
			if (incomeEntity.isPresent()) {
				Double empIncome = incomeEntity.get().getEmpIncome();
				Double propertyIncome = incomeEntity.get().getPropertyIncome();
				if (empIncome <= 300) {
					if (propertyIncome == 0) {
						eligibilityDetails.setPlanStatus("Approved");
						eligibilityDetails.setBeneficiaryAmt(350.00);
					} //if
					else {
						eligibilityDetails.setDenialReason("Property Income greater than 0");
						eligibilityDetails.setPlanStatus("Denied");
					} //else
				} //if
				else {
					eligibilityDetails.setDenialReason("Employee Income is Greater than 300$");
					eligibilityDetails.setPlanStatus("Denied");
				} //else
			} //if

		} //else if
		else if (planName.equals("Medicare")) {
			if (age >= 65) {
				eligibilityDetails.setPlanStatus("Approved");
				eligibilityDetails.setBeneficiaryAmt(200.00);
			} //if
			else {
				eligibilityDetails.setDenialReason("Citizen age is less than 65");
				eligibilityDetails.setPlanStatus("Denied");
			} //else
		} //else if
		else if (planName.equals("NJW")) {
			DCEducationEntity educationEntity = educationRepository.findByCaseNum(caseNumber);
			Integer graduationYear = educationEntity.getGraduationYear();
			int currentYear = LocalDate.now().getYear();
			Double empIncome = incomeEntity.get().getEmpIncome();
			if (empIncome == 0 && graduationYear < currentYear) {
				eligibilityDetails.setPlanStatus("Approved");
				eligibilityDetails.setBeneficiaryAmt(100.00);
			} //if
			else {
				eligibilityDetails.setDenialReason("Not Satisfied with income and graduation");
				eligibilityDetails.setPlanStatus("Denied");
			} //else

		} //else if
		if (eligibilityDetails.getPlanStatus().equals("Approved")) {
			eligibilityDetails.setDenialReason("NA");
			eligibilityDetails.setPlanStartDate(LocalDate.now());
			eligibilityDetails.setPlanEndDate(LocalDate.now().plusMonths(6));
		} //if
		return eligibilityDetails;
	}//determineEligibility(-,-,-)
}//class