package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.Quizziz.model.MCQQuestion;
import com.miniproject.Quizziz.service.MCQService;

@RestController
@CrossOrigin("*")
@RequestMapping("/mcqquestion")
public class MCQQuestionController {
	@Autowired MCQService mcqservice;
	
	@GetMapping("/getAllQuestions")
	public List<MCQQuestion> getAllMCQQuestions() {
		return mcqservice.getAllQuestions();
	}
	
	@PostMapping("/addQuestion")
	public MCQQuestion createMCQQuestion(@RequestBody MCQQuestion mcqQuestion) {
		return mcqservice.createQuestion(mcqQuestion);
	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public String deleteMCQQuestion (@PathVariable Integer id) {
		return mcqservice.deleteQuestion(id);
	}
	
	@PutMapping("/editQuestion/{id}")
	public MCQQuestion updateMCQQuestion (@PathVariable Integer id , @RequestBody MCQQuestion mcqQuestion) {
		return mcqservice.updateQuestion(id,mcqQuestion);
	}
}
