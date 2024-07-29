package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Quiz_Response;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.model.Teacher;
import com.miniproject.Quizziz.repository.QuestionRepository;
import com.miniproject.Quizziz.repository.Quiz_Response_Repository;
import com.miniproject.Quizziz.repository.StudentRepository;
import com.miniproject.Quizziz.service.Quiz_Response_Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Quiz_Response_ServiceImplementation implements Quiz_Response_Service {

	@Autowired
	Quiz_Response_Repository responseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	QuestionRepository questionRepo;

	@Override
	public List<Quiz_Response> getAllResponses() {
		return responseRepo.findAll();
	}

	@Override
	public Quiz_Response createResponse(Quiz_Response response, Integer student_id, Long question_id) {
		Student student = studentRepo.findById(student_id)
				.orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + student_id));
		response.setStudent(student);
		
		Question question = questionRepo.findById(question_id)
				.orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + question_id));
		response.setQuestion(question);
		
		student.getResponses().add(response);		
		question.getResponses().add(response);
		return responseRepo.save(response);
	}

	@Override
	public Quiz_Response editResponse(Long id, Quiz_Response response) {
		Quiz_Response updatedResponse = responseRepo.findById(id).orElse(null);
		updatedResponse.setFill_up_response(response.getFill_up_response());
		updatedResponse.setSelected_Option(response.getSelected_Option());
		return updatedResponse;
	}

	@Override
	public String deleteResponse(Long id) {
		responseRepo.deleteById(id);
		return "Response has been deleted";
	}

}
