package com.miniproject.Quizziz.service;

import java.util.List;
import java.util.Optional;

import com.miniproject.Quizziz.dto.StudentDto;
import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;

public interface StudentService {

	List<Student> getAllStudents();

	void addStudent(Student student);

	Student editStudentDetails(Integer id, Student student);

	String deleteStudent(Integer id);

	Optional<Student> login(Student student);

	void joinClassroom(Integer studentId, Long classroomId);

	void removeStudentFromClassroom(Integer studentId, Long classroomId);

	List<Classroom> getAllClassroomsForStudent(Integer studentId);

	List<Classroom> getClassroomsNotJoinedByStudent(Integer studentId);
	
}
