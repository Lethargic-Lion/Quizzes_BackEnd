package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Result;

public interface ResultService {

	Result addResult(Result result);

	List<Result> getAllResults();

	List<Result> getResultsByQuizID(Integer quiz_id);

	boolean doesResultExist(Integer student_id, Integer quiz_id);

	String resetStudentAttempt(Integer student_id, Integer quiz_id);

	

}
