package com.mk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.entity.Plans;

public interface PlanRepository extends JpaRepository<Plans,Integer>{

}
