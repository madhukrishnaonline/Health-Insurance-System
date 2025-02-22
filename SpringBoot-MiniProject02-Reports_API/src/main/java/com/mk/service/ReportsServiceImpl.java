package com.mk.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import com.mk.dto.ReportsRequest;
import com.mk.dto.ReportsResponse;
import com.mk.entity.EligibilityDetails;
import com.mk.repository.ReportsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServiceImpl implements IReportsService {

	@Autowired
	private ReportsRepository reportsRepository;

	@Override
	public List<String> getDistinctPlans() {
		/*List<EligibilityDetails> all = reportsRepository.findAll();
		List<String> plans = all.stream().map(plan -> plan.getPlanName()).distinct().collect(Collectors.toList());*/
		return reportsRepository.findAllPlans();
	}

	@Override
	public List<String> getDistinctStatus() {
		List<EligibilityDetails> all = reportsRepository.findAll();
		List<String> status = all.stream().map(aStatus -> aStatus.getPlanStatus()).distinct()
				.collect(Collectors.toList());

		return status;
	}

	@Override
	public List<ReportsResponse> search(ReportsRequest request) {

		EligibilityDetails queryBuilder = new EligibilityDetails();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			queryBuilder.setPlanName(request.getPlanName());
		} //if

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			queryBuilder.setPlanStatus(request.getPlanStatus());
		} //if

		if (request.getPlanStartDate() != null) {
			queryBuilder.setPlanStartDate(request.getPlanStartDate());
		} //if

		if (request.getPlanEndDate() != null) {
			queryBuilder.setPlanEndDate(request.getPlanEndDate());
		} //if

		Example<EligibilityDetails> example = Example.of(queryBuilder);

		List<EligibilityDetails> entities = reportsRepository.findAll(example);

		List<ReportsResponse> responses = new ArrayList<ReportsResponse>();
		for (EligibilityDetails entity : entities) {
			ReportsResponse response = new ReportsResponse();
			BeanUtils.copyProperties(entity, response);
			responses.add(response);
		} //for
		return responses;
	}

	@Override
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		List<EligibilityDetails> entities = reportsRepository.findAll();

		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("Reports-Api");

		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");

		int i = 1;
		for (EligibilityDetails entity : entities) {
			HSSFRow row = sheet.createRow(i);
			row.createCell(0).setCellValue(entity.getName());
			row.createCell(1).setCellValue(entity.getEmail());
			row.createCell(2).setCellValue(entity.getMobile());
			row.createCell(3).setCellValue(entity.getGender());
			row.createCell(4).setCellValue(entity.getSsn());

			i++;
		} //for
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}

	@Override
	public void generatePDFReport(HttpServletResponse response) throws Exception {
		List<EligibilityDetails> entities = reportsRepository.findAll();

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(20);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("Reports-Api", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(8f);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.RED);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Mobile", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		for (EligibilityDetails entity : entities) {
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(entity.getGender());
			table.addCell(String.valueOf(entity.getSsn()));
		} //for

		document.add(table);

		document.close();
	}

}//class