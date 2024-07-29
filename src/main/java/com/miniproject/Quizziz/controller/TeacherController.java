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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.miniproject.Quizziz.model.Teacher;
import com.miniproject.Quizziz.service.TeacherService;

@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/getAllTeachers")
	public List<Teacher> getTeacherList() {
		try {
			return teacherService.getAllTeachers();
		} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting Teacher list", e);
        }
		
	}
	
	@PostMapping("/addTeacher")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
		try {
			Teacher createdTeacher = teacherService.addTeacher(teacher);
			return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating teacher", e);
		}
	}
	
	@PutMapping("/editTeacherDetails/{id}")
	public ResponseEntity<Teacher> updateTeacherDetails(@PathVariable Integer id, @RequestBody Teacher teacher) {
		try {
			Teacher updatedTeacher = teacherService.editTeacherDetails(id, teacher);
			return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating teacher details", e);
		}
	}
	
	@DeleteMapping("deleteTeacherById/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTeacherById(@PathVariable Integer id) {
		try {
			teacherService.deleteTeacher(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting teacher", e);
		}
	}
}
