package com.mk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.entity.PlansCategory;

public interface PlanCategoryRepository extends JpaRepository<PlansCategory,Integer> {

}