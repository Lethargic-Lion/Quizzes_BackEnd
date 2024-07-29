package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniproject.Quizziz.model.MCQ_Option;
import com.miniproject.Quizziz.service.MCQ_Option_Service;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("/mcq_option_controller")
public class MCQ_Option_Controller {

    @Autowired
    private MCQ_Option_Service optionService;

    @GetMapping("/getAllOptions")
    public List<MCQ_Option> getOptionList() {
        try {
            return optionService.getAllOptions();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting options list", e);
        }
    }

    @PostMapping("/addOptions")
    public ResponseEntity<MCQ_Option> createNewOption(@RequestBody MCQ_Option option) {
        try {
            MCQ_Option createdOption = optionService.createOption(option);
            return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating option", e);
        }
    }

    @PutMapping("/editOption/{id}")
    public ResponseEntity<MCQ_Option> updateOptionDetails(@PathVariable Long id, @RequestBody MCQ_Option Option) {
        try {
            MCQ_Option updatedOption = optionService.editOption(id, Option);
            return new ResponseEntity<>(updatedOption, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating option details", e);
        }
    }

    @DeleteMapping("deleteOptionById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOptionById(@PathVariable Long id) {
        try {
            optionService.deleteOption(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting option", e);
        }
    }
}
