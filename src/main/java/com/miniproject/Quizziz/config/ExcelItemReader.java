package com.miniproject.Quizziz.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.miniproject.Quizziz.model.Address;
import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.model.UserKey;
import com.miniproject.Quizziz.repository.BatchRegistrationRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class ExcelItemReader implements ItemReader<Row> {
	
	private Iterator<Row> rowIterator;
	private String outputFilePath;

    public ExcelItemReader(String pathToFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(pathToFile));
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        this.rowIterator = sheet.iterator();
        
        if (rowIterator.hasNext()) {
            Row headerRow = rowIterator.next();
            addStatusAndReasonHeaders(headerRow);
        }
        
        outputFilePath = "C:\\DHCS\\BatchFiles\\output\\" + new File(pathToFile).getName().replace(".xlsx", "_output.xlsx");

    }

    @Override
    public Row read() {
        if (rowIterator.hasNext()) {
            return rowIterator.next();
        }
        return null;
    }
    
    private void addStatusAndReasonHeaders(Row headerRow) {
        Cell statusHeader = headerRow.createCell(headerRow.getLastCellNum());
        statusHeader.setCellValue("Status");
        Cell reasonHeader = headerRow.createCell(headerRow.getLastCellNum());
        reasonHeader.setCellValue("Reason");
    }
    
    public void addStatusAndReasonCells(Row row, String status, String reason) {
        int cellNum = row.getLastCellNum();
        Cell statusCell = row.createCell(cellNum++);
        statusCell.setCellValue(status);
        Cell reasonCell = row.createCell(cellNum);
        reasonCell.setCellValue(reason);
    }
}
