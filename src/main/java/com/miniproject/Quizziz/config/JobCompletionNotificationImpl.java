package com.miniproject.Quizziz.config;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class JobCompletionNotificationImpl implements JobExecutionListener{
	
	private Logger logger= LoggerFactory.getLogger(JobCompletionNotificationImpl.class);
	
    @Override
    public void beforeJob(JobExecution jobExecution) {
      logger.info("Job Started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
      if(jobExecution.getStatus()== BatchStatus.COMPLETED){
          logger.info("Job Completed");
      }
    }
//	@Autowired
//    private JavaMailSender mailSender;
//	
//    private final String filePath;
//
//    public JobCompletionNotificationImpl(@Value("#{jobParameters[fullPathFileName]}") String filePath) {
//        this.filePath = filePath;
//    }
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//        // No action needed before job
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        try {
//            sendExcelViaEmail();
//        } catch (IOException | MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sendExcelViaEmail() throws IOException, MessagingException {
//        FileInputStream fis = new FileInputStream(new File(filePath));
//        Workbook workbook = WorkbookFactory.create(fis);
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//        fis.close();
//        byte[] bytes = outputStream.toByteArray();
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.addAttachment("RegistrationDetails.xlsx", new ByteArrayResource(bytes));
//        helper.setTo("shchauhan@galaxe.com");
//        helper.setFrom("openproject-gxdigihealth@galaxe.com");
//        helper.setSubject("Registration Response");
//        helper.setText("The registration job has been completed. Please find the attached file for details.");
//        mailSender.send(message);
//    }
	
}
