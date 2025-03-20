package com.mk.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.EligibilityEntity;

@Repository
public interface EligibilityEntityRepo extends JpaRepository<EligibilityEntity,Serializable>{

}//interface