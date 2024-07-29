package com.miniproject.Quizziz.dto;

import java.util.List;

import com.miniproject.Quizziz.model.Fill_Ups;
import com.miniproject.Quizziz.model.MCQ_Option;
import com.miniproject.Quizziz.model.Question.QuestionType;
import com.miniproject.Quizziz.model.Quiz;
import com.miniproject.Quizziz.model.Quiz_Response;

import lombok.Data;

@Data
public class QuestionDto {
	
	private Long questionId;
	private String questionText;
	private QuestionType questionType;
	
	private List<MCQ_Option> mcqOptions;
	private Fill_Ups fill_up;
	private List<Quiz_Response> responses;
	private Quiz quiz;
}
