package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;

public interface ClassroomService {

	Classroom addClassroom(Integer teacherId, Classroom classroom);

	List<Classroom> getAllClassrooms();

	Classroom editClassroom(Long id, Classroom classroom);

	String deleteClassroom(Long id);

	void addStudentToClassroom(Long classroom_id, Integer student_id);

	List<Student> getStudentsByClassroomId(Long classroomId);
	
}
