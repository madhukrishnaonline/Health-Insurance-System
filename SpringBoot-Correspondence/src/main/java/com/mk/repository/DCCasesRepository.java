package com.mk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.DCCasesEntity;

@Repository
public interface DCCasesRepository extends JpaRepository<DCCasesEntity, Serializable> {

}//interface