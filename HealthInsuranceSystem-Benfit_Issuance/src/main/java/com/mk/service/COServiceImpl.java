package com.mk.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.binding.CitizenForm;
import com.mk.entity.EligibilityEntity;
import com.mk.repository.EligibilityEntityRepo;
import com.opencsv.CSVWriter;

@Service
public class COServiceImpl implements IBIService {

	@Autowired
	private EligibilityEntityRepo eligibilityEntityRepo;

	public void createcsv() {
		List<EligibilityEntity> appdetails = eligibilityEntityRepo.findByPlanStatus("Approved");
		List<CitizenForm> cforms = new ArrayList<CitizenForm>();

		for (EligibilityEntity app : appdetails) {
			CitizenForm citiform = new CitizenForm();
			BeanUtils.copyProperties(app, citiform);
			cforms.add(citiform);
		}

		//FTP Location
		String path = "M:\\SPRING\\csv.txt";
		File file = new File(path);
		try (FileWriter opfile = new FileWriter(file); CSVWriter writer = new CSVWriter(opfile);) {
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Casenum", "Name", "SSN", "BenefitAmt", "BankName", "AccontNumer" });

			for (CitizenForm cform : cforms) {
				data.add(new String[] { String.valueOf(cform.getCaseNum()), cform.getHolderName(),
						String.valueOf(cform.getHolderSsn()), String.valueOf(cform.getBeneficiaryAmt()),
						cform.getBankName(), cform.getAccountNumber() });
			}
			writer.writeAll(data);
		} catch (IOException e) {
			e.printStackTrace();
		} //catch
	}
}//class