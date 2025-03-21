package com.mk.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mk.dto.CitizenApp;
import com.mk.entity.CitizenAppEntity;
import com.mk.repository.CitizenAppRepository;

@Service
public class CitizenAppServiceImpl implements ICitizenAppService {

	@Autowired
	private CitizenAppRepository repository;

	@Override
	public Integer createCitizenApplication(CitizenApp citizenApp) {
		String url = "http://localhost:5050/ssa-web-api/ssn/{ssn}";

		WebClient webClient = WebClient.create();
		Optional<String> stateName = Optional
				.ofNullable(webClient.get().uri(url, citizenApp.getSsn()).retrieve().bodyToMono(String.class).block());

		if (stateName.isPresent() && stateName.get().equals("New Jersy")) {
			CitizenAppEntity appEntity = new CitizenAppEntity();
			BeanUtils.copyProperties(citizenApp, appEntity);
			appEntity.setStateName(stateName.get());
			CitizenAppEntity save = repository.save(appEntity);
			return save.getAppId();
		} //if
		return 0;
	}
}//class