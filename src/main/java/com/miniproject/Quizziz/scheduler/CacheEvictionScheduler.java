package com.miniproject.Quizziz.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.miniproject.Quizziz.serviceForTwilio.SmsService;

@Component
public class CacheEvictionScheduler {

    @Autowired
    private SmsService smsService;

//    @Scheduled(fixedRate = 30000)
//    public void evictExpiredOtps() {
//    	smsService.evictExpiredOtps();
//    	System.out.println("evictExpiredOTPs executed successfully!!");
//    }

}
