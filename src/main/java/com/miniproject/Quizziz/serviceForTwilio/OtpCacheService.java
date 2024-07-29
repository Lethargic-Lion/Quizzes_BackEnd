package com.miniproject.Quizziz.serviceForTwilio;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.dtoforTwilio.OtpCacheEntry;

@Service
public class OtpCacheService {

	@CachePut(cacheNames = "otpCache", key = "#username")
	public OtpCacheEntry generateOTP(String username) {
		String generatedOTP = new DecimalFormat("000000").format(new Random().nextInt(999999));
		LocalDateTime timestamp = LocalDateTime.now();
		return new OtpCacheEntry(generatedOTP, timestamp);
	}
	
	@CachePut(cacheNames = "otpCache", key = "#username")
	public OtpCacheEntry setOtpToNull(String username) {
		return new OtpCacheEntry(null, null);
	}

//	@CacheEvict(cacheNames = "otpCache", key = "#username")
//	public void evictOtpCacheEntry(String username) {
//		
//	}
	
	@CacheEvict(cacheNames = "otpCache", allEntries = true)
	public void evictAllCacheValues() {}
	
}
