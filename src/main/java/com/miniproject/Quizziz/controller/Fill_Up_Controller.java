package com.miniproject.Quizziz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.miniproject.Quizziz.model.Fill_Ups;
import com.miniproject.Quizziz.service.Fill_Up_Service;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
@RequestMapping("/fill_ups")
public class Fill_Up_Controller {

    @Autowired
    private Fill_Up_Service fill_up_Service;

    @GetMapping("/getFillUps")
    public List<Fill_Ups> getFillUpsList() {
        try {
            return fill_up_Service.getAllFillUps();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting fill-ups list", e);
        }
    }

    @PostMapping("/addFillUp")
    public ResponseEntity<Fill_Ups> createNewFillUp(@RequestBody Fill_Ups fill_up) {
        try {
            Fill_Ups createdFillUp = fill_up_Service.createFillUp(fill_up);
            return new ResponseEntity<>(createdFillUp, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating fill-up", e);
        }
    }

    @PutMapping("/editFillUp/{id}")
    public ResponseEntity<Fill_Ups> updateFillUpDetails(@PathVariable Long id, @RequestBody Fill_Ups fill_up) {
        try {
            Fill_Ups updatedFillUp = fill_up_Service.editFillUp(id, fill_up);
            return new ResponseEntity<>(updatedFillUp, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating fill-up details", e);
        }
    }

    @DeleteMapping("deleteFillUpById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFillUpById(@PathVariable Long id) {
        try {
            fill_up_Service.deleteFillUp(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting fill-up", e);
        }
    }
}
