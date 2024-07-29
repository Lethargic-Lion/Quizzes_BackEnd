package com.miniproject.Quizziz.serviceForTwilio;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.config.TwilioConfiguration;
import com.miniproject.Quizziz.dtoforTwilio.OtpCacheEntry;
import com.miniproject.Quizziz.dtoforTwilio.OtpRequest;
import com.miniproject.Quizziz.dtoforTwilio.OtpResponseDto;
import com.miniproject.Quizziz.dtoforTwilio.OtpStatus;
import com.miniproject.Quizziz.dtoforTwilio.OtpValidationRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	@Autowired
	private TwilioConfiguration twilioConfig;

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
    private OtpCacheService otpCacheService;

    public OtpResponseDto sendSMS(OtpRequest otpRequest) {
        OtpResponseDto otpResponseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
            OtpCacheEntry otpCacheEntry = otpCacheService.generateOTP(otpRequest.getUsername());
            String otp = otpCacheEntry.getOtp();
            String otpMessage = "Dear Customer, Your OTP is " + otp + ". Thank you.";
            Message message = Message.creator(to, from, otpMessage).create();
            otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            e.printStackTrace();
            otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
        }
        return otpResponseDto;
    }
	
    
    public String validateOtp(OtpValidationRequest otpValidationRequest) {
        Cache cache = cacheManager.getCache("otpCache");
        if (cache != null) {
        	OtpCacheEntry otpCacheEntry = cache.get(otpValidationRequest.getUsername(), OtpCacheEntry.class);
        	if(otpCacheEntry != null) {
        		if (otpCacheEntry.getTimestamp() == null || otpCacheEntry.getOtp()==null) {
                    return "Please Resend OTP and Try Again!!";
                }
        		if(otpCacheEntry.getTimestamp().plusSeconds(30).isBefore(LocalDateTime.now())) {
        			otpCacheService.setOtpToNull(otpValidationRequest.getUsername());
        			return "OTP has Expired";
        		}
        		if(otpCacheEntry.getOtp().equals(otpValidationRequest.getOtpNumber()) && otpCacheEntry.getTimestamp().plusSeconds(30).isAfter(LocalDateTime.now())) {
        			otpCacheService.setOtpToNull(otpValidationRequest.getUsername());
        			return "OTP is valid!";
        		}
        		else {
        			return "Please Enter Correct OTP!!";
        		}
        	}
        	return "No OTP for given Username";
        }
        return "Cache Not Found!!";
    }		
}

//    public void evictExpiredOtps() {
//        Cache cache = cacheManager.getCache("otpCache");
//        if (cache != null) {
//            ConcurrentMapCache concurrentMapCache = (ConcurrentMapCache) cache;
//            LocalDateTime now = LocalDateTime.now();
//            for (Map.Entry<Object, Object> entry : concurrentMapCache.getNativeCache().entrySet()) {
//                if (entry.getValue() instanceof OtpCacheEntry) {
//                    OtpCacheEntry otpEntry = (OtpCacheEntry) entry.getValue();
//                    if (otpEntry.getTimestamp().plusSeconds(10).isBefore(now)) {
////                        cache.evict(entry.getKey());
//                        otpCacheService.evictOtpCacheEntry(entry.getKey().toString());
//                        System.out.println("OTP expired for: "+ entry.getKey());
//                    }
//                }
//            }
//        }
//        System.out.println("Cache is null!!");
//    }
//
//
//	public String expireOtp(String username) {
//		otpCacheService.evictOtpCacheEntry(username);
//		return "Your OTP has Expired!!";
//	}