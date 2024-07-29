package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.service.QuestionService;
import com.miniproject.Quizziz.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/getAllQuestions")
    public List<Question> getQuestionList() {
    	try {
    		return questionService.getAllQuestions();
		} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting Question list", e);
        }
        
    }

    @PostMapping("/addQuestions")
    public ResponseEntity<Question> createNewQuestion(@RequestBody Question question) {
        try {
            Question createdQuestion = questionService.createQuestion(question);
            return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating question", e);
        }
    }

    @PutMapping("/editQuestion/{id}")
    public ResponseEntity<Question> updateQuestionDetails(@PathVariable Long id, @RequestBody Question question) {
        try {
            Question updatedQuestion = questionService.editQuestion(id, question);
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating question details", e);
        }
    }

    @DeleteMapping("deleteQuestionById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestionById(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting question", e);
        }
    }

    @PostMapping("/addQuestionToQuiz/{quizId}")
    public ResponseEntity<Question> addQuestionToQuiz(@PathVariable Integer quizId, @RequestBody Question question) {
        try {
            Question addedQuestion = quizService.addQuestionToQuiz(quizId, question);
            return new ResponseEntity<>(addedQuestion, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding question to quiz", e);
        }
    }

    @GetMapping("/getQuestionsByQuizId/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Integer quizId) {
        try {
            return questionService.getQuestionsByQuizId(quizId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting questions by quizId", e);
        }
    }
}
