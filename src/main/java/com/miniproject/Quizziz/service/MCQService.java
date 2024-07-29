package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.MCQQuestion;

public interface MCQService {

	MCQQuestion createQuestion(MCQQuestion mcqQuestion);

	String deleteQuestion(Integer id);

	MCQQuestion updateQuestion(Integer id, MCQQuestion mcqQuestion);

	List<MCQQuestion> getAllQuestions();

//	MCQQuestion getAllQuestions();

}
