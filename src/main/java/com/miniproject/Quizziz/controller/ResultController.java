package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Result;
import com.miniproject.Quizziz.service.ResultService;

@RestController
@CrossOrigin("*")
@RequestMapping("/result")
public class ResultController {

	@Autowired
	ResultService resultService;

	@PostMapping("/addResult")
	public ResponseEntity<Result> addResult(@RequestBody Result result) {
		try {
			Result createdResult = resultService.addResult(result);
			return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating result", e);
		}
	}

	@GetMapping("/getResults")
	public List<Result> getAllResults() {
		try {
			return resultService.getAllResults();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting Results", e);
		}
	}

	@GetMapping("/getResultsByQuizID/{quiz_id}")
	public List<Result> getResultsByQuizID(@PathVariable Integer quiz_id) {
		try {
			return resultService.getResultsByQuizID(quiz_id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting Results", e);
		}
	}

	@GetMapping("/checkResult/{student_id}/{quiz_id}")
	public ResponseEntity<Boolean> checkResult(@PathVariable Integer student_id, @PathVariable Integer quiz_id) {
		try {
			boolean resultExists = resultService.doesResultExist(student_id, quiz_id);
			return ResponseEntity.ok(resultExists);
		} catch (Exception e) {
			// Log the exception or handle it based on your application's requirements
			e.printStackTrace();
			return ResponseEntity.status(500).body(false); // Internal Server Error
		}
	}
	
	@DeleteMapping("/resetStudentAttempt/{student_id}/{quiz_id}")
	public String resetStudentAttempt (@PathVariable Integer student_id , @PathVariable Integer quiz_id) {
		try {
			return resultService.resetStudentAttempt(student_id,quiz_id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error resetting student attempt", e);
		}
	}
}
