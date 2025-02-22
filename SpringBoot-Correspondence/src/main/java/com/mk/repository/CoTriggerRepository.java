package com.mk.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.CoTriggerEntity;

@Repository
public interface CoTriggerRepository extends JpaRepository<CoTriggerEntity, Serializable> {

	List<CoTriggerEntity> findByTrgStatus(String status);

	CoTriggerEntity findByCaseNum(Long caseNumber);

}//class