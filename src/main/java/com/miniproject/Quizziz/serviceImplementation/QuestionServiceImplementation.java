package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.repository.QuestionRepository;
import com.miniproject.Quizziz.repository.QuizRepository;
import com.miniproject.Quizziz.service.QuestionService;

@Service
public class QuestionServiceImplementation implements QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Autowired
	QuizRepository quizRepo;

	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}

	@Override
	public Question createQuestion(Question question) {
		return questionRepo.save(question);
	}

	

	@Override
	public Question editQuestion(Long id, Question question) {
		Question updatedQuestion = questionRepo.findById(id).orElse(null);
		updatedQuestion.setQuestionText(question.getQuestionText());
		updatedQuestion.setQuestionType(question.getQuestionType());
		updatedQuestion.setMcqOptions(question.getMcqOptions());
		updatedQuestion.setFill_up(question.getFill_up());
//		updatedQuestion.setResponses(question.getResponses());
		questionRepo.save(updatedQuestion);
		return updatedQuestion;
	}

	@Override
	public String deleteQuestion(Long id) {
		questionRepo.deleteById(id);
		return "Question has been deleted";

	}

	@Override
	public List<Question> getQuestionsByQuizId(Integer quizId) {
		return questionRepo.findQuestionsByQuizId(quizId);
	}


}
