package com.miniproject.Quizziz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.miniproject.Quizziz.config.TwilioConfiguration;
import com.miniproject.Quizziz.serviceForTwilio.SmsService;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties
@EnableCaching
@EnableScheduling
public class QuizzizApplication {
	
	@Autowired private TwilioConfiguration twilioConfig;
	
	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuizzizApplication.class, args);
		
	}
}
