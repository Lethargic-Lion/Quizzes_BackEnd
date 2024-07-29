package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Result;
import com.miniproject.Quizziz.repository.ResultRepository;
import com.miniproject.Quizziz.service.ResultService;

@Service
public class ResultServiceImplementation implements ResultService{
	@Autowired ResultRepository resultRepo;

	@Override
	public Result addResult(Result result) {
		return resultRepo.save(result);
	}

	@Override
	public List<Result> getAllResults() {
		return resultRepo.findAll();
	}

	@Override
	public List<Result> getResultsByQuizID(Integer quiz_id) {
		return resultRepo.getResultsByQuizID(quiz_id);
	}

	@Override
	public boolean doesResultExist(Integer student_id, Integer quiz_id) {
		Result result = resultRepo.existsByStudentIdAndQuizId(student_id, quiz_id);
		if(result==null) {
			return false;
		}
		return true;
	}

	@Override
	public String resetStudentAttempt(Integer student_id, Integer quiz_id) {
		resultRepo.resetAttemptOfStudent(student_id, quiz_id);
		return "Attempt has been reset for the given Student!!";
	}

	
	
	
}
