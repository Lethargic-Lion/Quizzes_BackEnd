package com.miniproject.Quizziz.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.miniproject.Quizziz.model.Address;
import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.model.UserKey;
import com.miniproject.Quizziz.repository.BatchRegistrationRepo;

public class MyDataModelProcessor implements ItemProcessor<Row, User> {

	@Autowired
    private BatchRegistrationRepo batchRegistrationRepo;
	
	@Autowired
    private ExcelItemReader excelItemReader;

    @Override
    public User process(Row row) {
    	if (!isRowDataValid(row)) {
            excelItemReader.addStatusAndReasonCells(row, "Failed", "Invalid Data");
            return null;
        }
        if (isRowDataRepetitive(row)) {
            excelItemReader.addStatusAndReasonCells(row, "Failed", "User Already Registered");
            return null;
        }
        excelItemReader.addStatusAndReasonCells(row, "Successful", "New user");
        return createdUser(row);
    }

    private boolean isRowDataValid(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return false;
        }
        for (int cellNum = 0; cellNum < 23; cellNum++) {
            if (cellNum == 6 || cellNum == 12) {
                continue;
            }
            Cell cell = row.getCell(cellNum);
            if (cell == null || cell.getCellType() == CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowDataRepetitive(Row row) {
        UserKey userKey = UserKey.builder()
                .customerId(row.getCell(0).getStringCellValue())
                .employeeId(row.getCell(1).getStringCellValue())
                .organizationName(row.getCell(3).getStringCellValue())
                .build();
        return batchRegistrationRepo.existsById(userKey);
    }
    
    private User createdUser(Row row) {
		UserKey userKey = UserKey.builder().customerId(row.getCell(0).getStringCellValue())
				.employeeId(row.getCell(1).getStringCellValue()).organizationName(row.getCell(3).getStringCellValue())
				.build();

		User user = User.builder().userKey(userKey).policyNumber((long) row.getCell(2).getNumericCellValue())
				.insuranceCompany(row.getCell(4).getStringCellValue()).firstName(row.getCell(5).getStringCellValue())
				.middleName(row.getCell(6).getStringCellValue()).lastName(row.getCell(7).getStringCellValue())
				.ssn(String.valueOf((long) row.getCell(8).getNumericCellValue()))
				.email(row.getCell(9).getStringCellValue())
				.mobile(String.valueOf((long) row.getCell(10).getNumericCellValue()))
				.dob(row.getCell(11).getLocalDateTimeCellValue().toLocalDate())
				.occupation(row.getCell(12).getStringCellValue()).build();

		Address homeAddress = Address.builder().type("Residence").street(row.getCell(13).getStringCellValue())
				.state(row.getCell(14).getStringCellValue()).city(row.getCell(15).getStringCellValue())
				.telephone(String.valueOf((long) row.getCell(16).getNumericCellValue()))
				.zipcode(String.valueOf((long) row.getCell(17).getNumericCellValue())).user(user).build();

		Address officeAddress = Address.builder().type("Office").street(row.getCell(18).getStringCellValue())
				.state(row.getCell(19).getStringCellValue()).city(row.getCell(20).getStringCellValue())
				.telephone(String.valueOf((long) row.getCell(21).getNumericCellValue()))
				.zipcode(String.valueOf((long) row.getCell(22).getNumericCellValue())).user(user).build();

		List<Address> addresses = new ArrayList<>();
		addresses.add(homeAddress);
		addresses.add(officeAddress);
		user.setAddresses(addresses);
		return user;
	}

}
