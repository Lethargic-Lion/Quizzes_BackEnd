package com.miniproject.Quizziz.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.serviceImplementation.BatchRegistrationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/users")
public class BatchRegistrationController {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	BatchRegistrationService batchRegistrationService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Job excelJob;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
		}

		try {
			InputStream inputStream = file.getInputStream();
			batchRegistrationService.processExcelFile(inputStream);
			return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to upload file!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private final String INPUT_STORAGE = "C:\\DHCS\\BatchFiles\\input\\";

	@PostMapping("/uploadExcel")
	public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
		try {
			String originalFileName = file.getOriginalFilename();
			String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	        String newFileName = currentTime+ "_" + originalFileName;
			File fileToImport = new File(INPUT_STORAGE + newFileName);
			file.transferTo(fileToImport);

			JobParameters jobParameters = new JobParametersBuilder()
					.addString("fullPathFileName", INPUT_STORAGE + newFileName)
					.addLong("startAt", System.currentTimeMillis()).toJobParameters();

			jobLauncher.run(excelJob, jobParameters);

//			try {
//				sendExcelViaEmail(fileToImport);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}

			return "Excel file uploaded and Spring Batch job triggered successfully!";
		} catch (

		Exception e) {
			e.printStackTrace();
			return "Error uploading Excel file: " + e.getMessage();
		}
	}

	private void sendExcelViaEmail(File file) throws MessagingException, EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fis);
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
