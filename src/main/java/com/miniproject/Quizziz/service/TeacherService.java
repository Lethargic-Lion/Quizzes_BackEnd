package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Teacher;

public interface TeacherService {

	List<Teacher> getAllTeachers();

	Teacher addTeacher(Teacher teacher);

	Teacher editTeacherDetails(Integer id, Teacher teacher);

	String deleteTeacher(Integer id);
	
}
