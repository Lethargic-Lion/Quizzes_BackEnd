package com.miniproject.Quizziz.dto;

import lombok.Data;

@Data
public class MCQ_OptionDto {
	private Long optionId;
	private String optionDescription;
    private boolean isCorrect;
}
