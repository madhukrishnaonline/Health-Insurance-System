package com.mk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.DCEducationEntity;

@Repository
public interface DCEducationRepository extends JpaRepository<DCEducationEntity,Serializable>{

	DCEducationEntity findByCaseNum(Long caseNum);
	
}//interface