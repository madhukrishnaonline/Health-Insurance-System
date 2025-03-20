package com.mk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.entity.SSAEntity;
import com.mk.repository.SSARepository;

@Service
public class SSAService {
	
	@Autowired
	private SSARepository repository;
	
	public String registerSSN(SSAEntity entity)
	{
	   repository.save(entity);
	   return "created";
	}
	
	public String getBySSN(Integer ssn) {
		Optional<SSAEntity> optional = repository.findBySsn(ssn);
		if(optional.isPresent())
		{
			 SSAEntity entity = optional.get();
			 return entity.getStateName();
		}//if
		return null;
	}//getBySSN
	
	
	public List<SSAEntity> getAllSSNs(){
	  return repository.findAll();	
	}
}//class