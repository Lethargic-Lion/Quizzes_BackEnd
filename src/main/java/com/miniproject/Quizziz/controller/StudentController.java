package com.miniproject.Quizziz.controller;

import java.util.List;
import java.util.Optional;

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

import com.miniproject.Quizziz.ExceptionHandler.StudentRegistrationException;
import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.service.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Student student) {
		Optional<Student> studentOptional = studentService.login(student);
		if (studentOptional.isPresent()) {
			Student loggedInStudent = studentOptional.get();
			return ResponseEntity.ok(loggedInStudent);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@GetMapping("/getAllStudents")
	public List<Student> getStudentList() {
		try {
			return studentService.getAllStudents();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error getting student list", e);
		}
	}

	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		try {
			studentService.addStudent(student);
			return ResponseEntity.ok("Student added successfully");
		} catch (StudentRegistrationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/editStudentDetails/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable Integer id, @RequestBody Student student) {
		try {
			Student updatedStudent = studentService.editStudentDetails(id, student);
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating student details", e);
		}
	}

	@DeleteMapping("deleteStudentById/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudentById(@PathVariable Integer id) {
		try {
			studentService.deleteStudent(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting student", e);
		}
	}

	@PostMapping("/joinClassroom/{studentId}/{classroomId}")
	public ResponseEntity<String> joinClassroom(@PathVariable Integer studentId, @PathVariable Long classroomId) {
		try {
			studentService.joinClassroom(studentId, classroomId);
			return ResponseEntity.ok("Student joined classroom successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/removeFromClassroom/{studentId}/{classroomId}")
	public ResponseEntity<String> removeStudentFromClassroom(@PathVariable Integer studentId,
			@PathVariable Long classroomId) {
		try {
			studentService.removeStudentFromClassroom(studentId, classroomId);
			return ResponseEntity.ok("Student removed from the classroom successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/getClassroomsByStudentID/{studentId}")
    public ResponseEntity<List<Classroom>> getAllClassroomsForStudent(@PathVariable Integer studentId) {
        try {
            List<Classroom> classrooms = studentService.getAllClassroomsForStudent(studentId);
            return ResponseEntity.ok(classrooms);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
	@GetMapping("/classroomsNotJoined/{studentId}")
    public ResponseEntity<List<Classroom>> getClassroomsNotJoinedByStudent(@PathVariable Integer studentId) {
        try {
            List<Classroom> notJoinedClassrooms = studentService.getClassroomsNotJoinedByStudent(studentId);
            return ResponseEntity.ok(notJoinedClassrooms);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
