package com.miniproject.Quizziz.dto;

import java.util.List;

import com.miniproject.Quizziz.model.Quiz;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.model.Teacher;

import lombok.Data;

@Data
public class ClassroomDto {
	
	private Long classroom_id;
	private String title;
	private String description;
    private Teacher teacher;
    
	private List<Student> students;
	private List<Quiz> quizzes;
}
