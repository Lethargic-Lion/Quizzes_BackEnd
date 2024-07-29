package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Teacher;
import com.miniproject.Quizziz.repository.QuizRepository;
import com.miniproject.Quizziz.repository.TeacherRepository;
import com.miniproject.Quizziz.service.TeacherService;

@Service
public class TeacherServiceImplementation implements TeacherService{
		
	@Autowired TeacherRepository teacherRepo;
	@Autowired QuizRepository quizRepo;
	
	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepo.findAll();
	}

	@Override
	public Teacher addTeacher(Teacher teacher) {
		return teacherRepo.save(teacher);
	}

	@Override
	public Teacher editTeacherDetails(Integer id, Teacher teacher) {
		Teacher updatedTeacher = teacherRepo.findById(id).orElse(null);
		updatedTeacher.setPassword(teacher.getPassword());
		updatedTeacher.setUsername(teacher.getUsername());
		teacherRepo.save(updatedTeacher);
		return updatedTeacher;
	}
	
	@Override
	public String deleteTeacher(Integer id) {
		teacherRepo.deleteById(id);
		return "Teacher has been deleted";
	}

}
