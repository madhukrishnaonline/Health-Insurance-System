package com.mk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mk.entity.EligibilityEntity;

public interface ReportsRepository extends JpaRepository<EligibilityEntity, Integer> {
	@Query("select distinct(planName) from EligibilityEntity")
	List<String> findAllPlans();
}