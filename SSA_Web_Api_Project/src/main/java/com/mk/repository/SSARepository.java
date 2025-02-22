package com.mk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.entity.SSAEntity;

@Repository
public interface SSARepository extends JpaRepository<SSAEntity, Integer> {
	Optional<SSAEntity> findBySsn(Integer ssn);
}//class