package com.mk.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mk.entity.CitizenAppEntity;
import com.mk.entity.CoTriggerEntity;
import com.mk.entity.EligibilityEntity;
import com.mk.mail.EmailUtils;
import com.mk.repository.CitizenAppRepository;
import com.mk.repository.CoTriggerRepository;
import com.mk.repository.DCCasesRepository;
import com.mk.repository.EligibilityEntityRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class COServiceImpl implements IBIService {

	@Autowired
	private EligibilityEntityRepo eligibilityEntityRepo;

	@Autowired
	private CitizenAppRepository citizenAppRepository;

	@Autowired
	private CoTriggerRepository triggerRepository;

	@Autowired
	private DCCasesRepository casesRepository;

	@Autowired
	private EmailUtils email;


	

	private String sendMailBody(EligibilityEntity entity, String fileName) {
		String mailBody = null;
		try (FileReader reader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(reader);) {
			StringBuilder builder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				builder.append(line);
				line = bufferedReader.readLine();
			} //while
			mailBody = builder.toString();
			mailBody = mailBody.replace("{FULLNAME}", entity.getHolderName());
			mailBody = mailBody.replace("{PLAN}", entity.getPlanName());
			mailBody = mailBody.replace("{PLANSTATUS}", entity.getPlanStatus());
		} //try
		catch (Exception e) {
			log.error("Exception Occured " + e.getMessage());
		} //catch
		return mailBody;
	}//sendMail

	private void generatePDFReport(EligibilityEntity eligibleEntity, CitizenAppEntity appEntity) throws Exception {
		Document document = new Document(PageSize.A4);

		File file = new File(eligibleEntity.getCaseNum() + ".pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(20);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("Plan_Eligibility_Details", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3.5f, 2.0f, 2.5f, 3.0f, 3.0f, 2.5f, 3.5f, 3.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(8f);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.cyan);

		cell.setPhrase(new Phrase("Citizen_Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan_Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan_Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan_Start_Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan_End_Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Benfit_Amount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Denial_Reason", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		table.addCell(eligibleEntity.getHolderName());
		table.addCell(eligibleEntity.getPlanName());
		table.addCell(eligibleEntity.getPlanStatus());
		table.addCell(String.valueOf(eligibleEntity.getPlanStartDate()));
		table.addCell(String.valueOf(eligibleEntity.getPlanEndDate()));
		table.addCell(String.valueOf(eligibleEntity.getBeneficiaryAmt()));
		table.addCell(eligibleEntity.getDenialReason());
		table.addCell(String.valueOf(eligibleEntity.getHolderSsn()));

		document.add(table);
		document.close();
		pdfWriter.flush();
		fos.close();

		String mailBody = sendMailBody(eligibleEntity, "ELIGIBILITY_NOTICE.txt");
		String subject = "Eligibility_Report";
		email.sendMail(appEntity.getEmail(), subject, mailBody, file);
		updateTriggerData(eligibleEntity.getCaseNum(), file);

		file.delete();
	}//generatePDF

	private void updateTriggerData(Long caseNumber, File file) throws Exception {
		CoTriggerEntity triggerEntity = triggerRepository.findByCaseNum(caseNumber);

		/*byte[] arr = new byte[(byte)file.length()];
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(arr);
		
		triggerEntity.setCoPdf(arr);*/
		triggerEntity.setTrgStatus("Completed");
		triggerRepository.save(triggerEntity);

		//		inputStream.close();
	}//updateTriggerData

}//class