package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Quiz;
import com.miniproject.Quizziz.service.QuizService;
import com.miniproject.Quizziz.service.TeacherService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAllQuiz")
    public List<Quiz> getQuizList() {
        try {
            return quizService.getAllQuizzes();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting quiz list", e);
        }
    }  

    @PostMapping("/addQuiz")
    public ResponseEntity<Quiz> createNewQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz createdQuiz = quizService.createQuiz(quiz);
            return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating quiz", e);
        }
    }

    @PutMapping("/editQuiz/{id}")
    public ResponseEntity<Quiz> updateQuizDetails(@PathVariable Integer id, @RequestBody Quiz quiz) {
        try {
            Quiz updatedQuiz = quizService.editQuiz(id, quiz);
            return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating quiz details", e);
        }
    }

    @DeleteMapping("deleteQuizById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuizById(@PathVariable Integer id) {
        try {
            quizService.deleteQuiz(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting quiz", e);
        }
    }

    @PostMapping("/addTeacherIdToQuiz/{teacherId}")
    public ResponseEntity<Quiz> addTeacherIdToQuiz(@PathVariable Integer teacherId, @RequestBody Quiz quiz) {
        try {
            Quiz quizWithTeacher = quizService.addTeacherIdToQuiz(teacherId, quiz);
            return new ResponseEntity<>(quizWithTeacher, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding teacherID to quiz", e);
        }
    }
    
    @PostMapping("/addQuizToClassroom/{teacherId}/{classroomID}")
    public ResponseEntity<Quiz> addQuizToClassroom(@PathVariable Integer teacherId , @PathVariable Long classroomID , @RequestBody Quiz quiz){
    	try {
			Quiz classroomQuiz = quizService.addQuizToClassroom(teacherId,classroomID,quiz);
			return new ResponseEntity<>(classroomQuiz,HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding quiz to classroom", e);
		}
    }
    
    @GetMapping("/getQuizByClassroomID/{classroomID}")
    public List<Quiz> getQuizByClassroomID(@PathVariable Long classroomID) {
        try {
            return quizService.getQuizByClassroomID(classroomID);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting quizzes by classroom id", e);
        }
    }
}
