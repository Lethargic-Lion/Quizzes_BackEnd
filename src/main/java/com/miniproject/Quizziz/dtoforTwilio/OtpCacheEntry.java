package com.miniproject.Quizziz.dtoforTwilio;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpCacheEntry {
	private String otp;
    private LocalDateTime timestamp;
}
