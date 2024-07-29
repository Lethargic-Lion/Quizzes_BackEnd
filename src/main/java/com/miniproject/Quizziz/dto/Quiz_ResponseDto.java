package com.miniproject.Quizziz.dto;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Student;

public class Quiz_ResponseDto {
	
	private Long response_id;
	private String selected_Option;
	private String fill_up_response;

    private Question question;
	
    private Student student;
}
