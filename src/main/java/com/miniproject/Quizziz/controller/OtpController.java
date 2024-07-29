package com.miniproject.Quizziz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.Quizziz.dtoforTwilio.OtpRequest;
import com.miniproject.Quizziz.dtoforTwilio.OtpResponseDto;
import com.miniproject.Quizziz.dtoforTwilio.OtpValidationRequest;
import com.miniproject.Quizziz.serviceForTwilio.SmsService;

@RestController
@RequestMapping("/otp")
public class OtpController {
	@Autowired SmsService smsService;
	
	@PostMapping("/send-otp")
	public OtpResponseDto sendOtp(@RequestBody OtpRequest otpRequest) {
		return smsService.sendSMS(otpRequest);
	}
	
	@PostMapping("/validate-otp")
    public String validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {
//		log.info("inside validateOtp :: "+otpValidationRequest.getUsername()+" "+otpValidationRequest.getOtpNumber());
		return smsService.validateOtp(otpValidationRequest);
	}
	
//	@DeleteMapping("/expire-otp")
//	public String expireOtp(@RequestBody String username) {
//		return smsService.expireOtp(username);
//	}	
}
