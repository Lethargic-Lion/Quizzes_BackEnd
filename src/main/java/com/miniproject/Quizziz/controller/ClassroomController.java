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

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.service.ClassroomService;
import com.miniproject.Quizziz.service.TeacherService;

@RestController
@CrossOrigin("*")
@RequestMapping("/classroom")
public class ClassroomController {
	
	@Autowired ClassroomService classroomService;
	@Autowired TeacherService teacherService;
	
	@PostMapping("/addClassroom/{teacherId}")
    public ResponseEntity<Classroom> addClassroom(@PathVariable Integer teacherId, @RequestBody Classroom classroom) {
        try {
        	Classroom newClassroom = classroomService.addClassroom(teacherId, classroom);
            return new ResponseEntity<>(newClassroom, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error adding classroom", e);
        }
    }
	
	@GetMapping("/getAllClassrooms")
    public List<Classroom> getAllClassrooms() {
    	try {
    		return classroomService.getAllClassrooms();
		} catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting Classroom list", e);
        }
    }
	
	@PutMapping("/editClassroom/{id}")
    public ResponseEntity<Classroom> editClassroom(@PathVariable Long id, @RequestBody Classroom classroom) {
        try {
        	Classroom updatedClassroom = classroomService.editClassroom(id, classroom);
            return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating classroom details", e);
        }
    }
	
	@DeleteMapping("deleteClassroom/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteClassroom(@PathVariable Long id) {
        try {
            return classroomService.deleteClassroom(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting Classroom", e);
        }
    }
	
	@PostMapping("/addStudentToClassroom/{classroom_id}/{student_id}")
	public ResponseEntity<String> addStudentToClassroom(@PathVariable Long classroom_id,
			@PathVariable Integer student_id) {

		try {
			classroomService.addStudentToClassroom(classroom_id, student_id);
			return ResponseEntity.ok("Student joined classroom successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/getStudentsByClassroomId/{classroomId}")
    public ResponseEntity<List<Student>> getStudentsByClassroomId(@PathVariable Long classroomId) {
        try {
            List<Student> students = classroomService.getStudentsByClassroomId(classroomId);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
}
