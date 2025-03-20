package com.mk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mk.entity.EligibilityDetails;

public interface ReportsRepository extends JpaRepository<EligibilityDetails, Integer> {
	@Query("select distinct(planName) from EligibilityDetails")
	List<String> findAllPlans();
}