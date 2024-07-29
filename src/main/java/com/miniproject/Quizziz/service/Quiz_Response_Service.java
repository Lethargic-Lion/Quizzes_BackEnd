package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Quiz_Response;

public interface Quiz_Response_Service {

	List<Quiz_Response> getAllResponses();

	Quiz_Response createResponse(Quiz_Response response, Integer student_id, Long question_id);

	Quiz_Response editResponse(Long id, Quiz_Response response);

	String deleteResponse(Long id);
	
}
