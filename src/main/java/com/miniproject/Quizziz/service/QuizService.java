package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Quiz;

public interface QuizService {

	List<Quiz> getAllQuizzes();

	Quiz createQuiz(Quiz quiz);

	String deleteQuiz(Integer id);

	Quiz editQuiz(Integer id, Quiz quiz);

	Question addQuestionToQuiz(Integer quizId, Question question);

	Quiz addTeacherIdToQuiz(Integer teacherId, Quiz quiz);

	Quiz addQuizToClassroom(Integer teacherId, Long classroomID, Quiz quiz);

	List<Quiz> getQuizByClassroomID(Long classroomID);
	
}
