package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniproject.Quizziz.model.Quiz_Response;
import com.miniproject.Quizziz.service.Quiz_Response_Service;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz_response")
public class Quiz_Response_Controller {

    @Autowired
    private Quiz_Response_Service quizResponseService;

    @GetMapping("/getAllResponses")
    public List<Quiz_Response> getResponseList() {
        try {
            return quizResponseService.getAllResponses();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting quiz responses list", e);
        }
    }

    @PostMapping("/addResponse/{student_id}/{question_id}")
    public ResponseEntity<Quiz_Response> createNewResponse(@RequestBody Quiz_Response response , @PathVariable Integer student_id , @PathVariable Long question_id) {
        try {
            Quiz_Response createdResponse = quizResponseService.createResponse(response , student_id , question_id);
            return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating quiz response", e);
        }
    }

    @PutMapping("/editResponse/{id}")
    public ResponseEntity<Quiz_Response> updateResponse(@PathVariable Long id, @RequestBody Quiz_Response response) {
        try {
            Quiz_Response updatedResponse = quizResponseService.editResponse(id, response);
            return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating quiz response", e);
        }
    }

    @DeleteMapping("deleteResponseById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResponseById(@PathVariable Long id) {
        try {
            quizResponseService.deleteResponse(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting quiz response", e);
        }
    }
}
