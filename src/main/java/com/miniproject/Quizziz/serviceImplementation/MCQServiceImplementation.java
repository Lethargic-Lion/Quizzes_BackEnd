package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.MCQQuestion;
import com.miniproject.Quizziz.repository.MCQQuestionRepository;
import com.miniproject.Quizziz.service.MCQService;

@Service
public class MCQServiceImplementation implements MCQService {
	
	@Autowired MCQQuestionRepository mcqRepo;
	
	@Override
	public MCQQuestion createQuestion(MCQQuestion mcqquestion) {
		MCQQuestion newQuestion = mcqRepo.save(mcqquestion);
		return newQuestion;
	}
	
	@Override
	public String deleteQuestion(Integer id) {
		mcqRepo.deleteById(id);
		return "Question has been Deleted";
	}
	
	@Override
	public MCQQuestion updateQuestion(Integer id, MCQQuestion newQuestion) {
		MCQQuestion updatedQuestion = mcqRepo.findById(id).orElse(null);
		updatedQuestion.setQuestionDescription(newQuestion.getQuestionDescription());
		updatedQuestion.setOption1(newQuestion.getOption1());
		updatedQuestion.setOption2(newQuestion.getOption2());
		updatedQuestion.setOption3(newQuestion.getOption3());
		updatedQuestion.setCorrectOption(newQuestion.getCorrectOption());
		mcqRepo.save(updatedQuestion);
		return updatedQuestion;
	}

	@Override
	public List<MCQQuestion> getAllQuestions() {
		return mcqRepo.findAll();
	}

	

	
	
	

}
