package com.mk.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.DCChildrensEntity;

@Repository
public interface DCChildrensRepository extends JpaRepository<DCChildrensEntity,Serializable>{

	List<DCChildrensEntity> findByCaseNum(Long caseNum);
}//interface