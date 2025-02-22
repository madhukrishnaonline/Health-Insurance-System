package com.mk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.DCIncomeEntity;

@Repository
public interface DCIncomeRepository extends JpaRepository<DCIncomeEntity,Serializable>{

	DCIncomeEntity findByCaseNum(Long caseNum);
	
}//interface