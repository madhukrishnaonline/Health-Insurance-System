package com.mk.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.mk.entity.EligibilityDetails;
import com.mk.repository.ReportsRepository;

//@Component
public class AppRunnerTest implements CommandLineRunner {

	@Autowired
	private ReportsRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		EligibilityDetails entities = new EligibilityDetails();
		entities.setId(1);
		entities.setName("madhu");
		entities.setPlanName("food");
		entities.setPlanStatus("Approved");
		entities.setEmail("madhu@gmail.com");
		entities.setMobile(9121493579L);
		entities.setGender("male");
		entities.setSsn(9889724);
		repository.save(entities);
		
		EligibilityDetails entities1 = new EligibilityDetails();
		entities1.setId(2);
		entities1.setName("krishna");
		entities1.setPlanName("entertainment");
		entities1.setPlanStatus("Denied");
		entities1.setEmail("krishna@gmail.com");
		entities1.setMobile(9121756579L);
		entities1.setGender("male");
		entities1.setSsn(988976473);
		repository.save(entities1);
		
		EligibilityDetails entities2 = new EligibilityDetails();
		entities2.setId(3);
		entities2.setName("lakshmi");
		entities2.setPlanName("food");
		entities2.setPlanStatus("Approved");
		entities2.setEmail("lakshmi@gmail.com");
		entities2.setMobile(91214987579L);
		entities2.setGender("female");
		entities2.setSsn(9883424);
		repository.save(entities2);
	}
}//class