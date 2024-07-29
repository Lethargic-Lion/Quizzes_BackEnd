package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Question;

public interface QuestionService {

	List<Question> getAllQuestions();

	Question createQuestion(Question question);

	Question editQuestion(Long id, Question question);

	String deleteQuestion(Long id);

	List<Question> getQuestionsByQuizId(Integer quizId);

	

	

}
