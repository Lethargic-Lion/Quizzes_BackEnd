package com.miniproject.Quizziz.serviceImplementation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Address;
import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.model.UserKey;
import com.miniproject.Quizziz.repository.BatchRegistrationRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class BatchRegistrationService {

	@Autowired
	BatchRegistrationRepo batchRegistrationRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void processExcelFile(InputStream inputStream) throws IOException, MessagingException {
	    Workbook workbook = WorkbookFactory.create(inputStream);
	    Sheet sheet = workbook.getSheetAt(0);
	    int lastRowNum = sheet.getLastRowNum();

	    for (int i = 1; i <= lastRowNum; i++) {
	        Row row = sheet.getRow(i);

	        if (!isRowDataValid(row)) {
	            addStatusAndReasonCells(row, "Failed", "Invalid Data");
	            continue;
	        }
	        if (isRowDataRepetitive(row)) {
	            addStatusAndReasonCells(row, "Failed", "User Already Registered");
	            continue;
	        } else {
	            createUserFromRow(row);
	        }
	    }
	    addStatusAndReasonHeaders(sheet.getRow(1));
	    sendExcelViaEmail(workbook);
	    workbook.close();
	}

	
//	public void processExcelFile(InputStream inputStream) throws IOException, MessagingException {
//		Workbook workbook = WorkbookFactory.create(inputStream);
//		Sheet sheet = workbook.getSheetAt(0); 
//		Iterator<Row> rowIterator = sheet.iterator();
//
//		if (rowIterator.hasNext()) {
//			rowIterator.next();
//		}
//		if (rowIterator.hasNext()) {
//			rowIterator.next();
//		}
//
//		while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			if(!isRowDataValid(row)) {
//				addStatusAndReasonCells(row, "Failed", "Invalid Data");
//				continue;
//			}
//			if(isRowDataRepetitive(row)) {
//				addStatusAndReasonCells(row, "Failed", "User Already Registered");
//				continue;
//			}
//			else {
//				createUserFromRow(row);
//			}
//		}
//		addStatusAndReasonHeaders(sheet.getRow(1));
//		sendExcelViaEmail(workbook);
//		workbook.close();
//	}
	
	private void addStatusAndReasonHeaders(Row headerRow) {
		Cell statusHeader = headerRow.createCell(headerRow.getLastCellNum());
        statusHeader.setCellValue("Status");
        Cell reasonHeader = headerRow.createCell(headerRow.getLastCellNum());
        reasonHeader.setCellValue("Reason");
	}
	
	private void addStatusAndReasonCells(Row row, String status, String reason) {
        Cell statusCell = row.createCell(row.getLastCellNum());
        statusCell.setCellValue(status);
        Cell reasonCell = row.createCell(row.getLastCellNum());
        reasonCell.setCellValue(reason);
    }

	private boolean isRowDataValid(Row row) {
		if (row == null) {
	        return false;
	    }
	    if (row.getLastCellNum() <= 0) {
	        return false;
	    }
	    for (int cellNum = 0; cellNum < 23; cellNum++) {
	    	if(cellNum==6 || cellNum==12) {
	        	continue;
	        }
	        Cell cell = row.getCell(cellNum);
	        if (cell == null || cell.getCellType() == CellType.BLANK ) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	private boolean isRowDataRepetitive(Row row) {
		UserKey userKey = UserKey.builder().customerId(row.getCell(0).getStringCellValue())
				.employeeId(row.getCell(1).getStringCellValue()).organizationName(row.getCell(3).getStringCellValue())
				.build();
		if(batchRegistrationRepo.existsById(userKey)) {
			return true;
		}
		return false;
	}

	private void createUserFromRow(Row row) {
		UserKey userKey = UserKey.builder().customerId(row.getCell(0).getStringCellValue())
				.employeeId(row.getCell(1).getStringCellValue()).organizationName(row.getCell(3).getStringCellValue())
				.build();

		User user = User.builder().userKey(userKey)
				.policyNumber((long) row.getCell(2).getNumericCellValue())
				.insuranceCompany(row.getCell(4).getStringCellValue())
				.firstName(row.getCell(5).getStringCellValue())
				.middleName(row.getCell(6).getStringCellValue())
				.lastName(row.getCell(7).getStringCellValue())
				.ssn(String.valueOf((long) row.getCell(8).getNumericCellValue()))
				.email(row.getCell(9).getStringCellValue())
				.mobile(String.valueOf((long) row.getCell(10).getNumericCellValue()))
				.dob(row.getCell(11).getLocalDateTimeCellValue().toLocalDate())
				.occupation(row.getCell(12).getStringCellValue()).build();

		Address homeAddress = Address.builder()
				.type("Residence")
				.street(row.getCell(13).getStringCellValue())
				.state(row.getCell(14).getStringCellValue())
				.city(row.getCell(15).getStringCellValue())
				.telephone(String.valueOf((long) row.getCell(16).getNumericCellValue()))
				.zipcode(String.valueOf((long) row.getCell(17).getNumericCellValue()))
				.user(user).build();

		Address officeAddress = Address.builder()
				.type("Office")
				.street(row.getCell(18).getStringCellValue())
				.state(row.getCell(19).getStringCellValue())
				.city(row.getCell(20).getStringCellValue())
				.telephone(String.valueOf((long) row.getCell(21).getNumericCellValue()))
				.zipcode(String.valueOf((long) row.getCell(22).getNumericCellValue()))
				.user(user).build();

		List<Address> addresses = new ArrayList<>();
		addresses.add(homeAddress);
		addresses.add(officeAddress);
		user.setAddresses(addresses);
		batchRegistrationRepo.save(user);
		addStatusAndReasonCells(row, "Successful", "New user");
	}
	
	public void sendExcelViaEmail(Workbook workbook)throws IOException, MessagingException{
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    workbook.write(outputStream);
	    byte[] bytes = outputStream.toByteArray();

	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    helper.addAttachment("RegistrationDetails.xlsx", new ByteArrayResource(bytes));
	    helper.setTo("shchauhan@galaxe.com");
	    helper.setFrom("openproject-gxdigihealth@galaxe.com");
	    helper.setSubject("Registration Response");
	    helper.setText("Body");
	    mailSender.send(message);
	}

}
