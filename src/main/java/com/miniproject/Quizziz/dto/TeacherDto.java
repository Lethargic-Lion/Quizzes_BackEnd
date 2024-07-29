package com.miniproject.Quizziz.dto;

import java.util.List;

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Quiz;

public class TeacherDto {
	private int teacher_id;
	private String username;
	private String password;
	
	
	private List<Quiz> quizzes;	
    private List<Classroom> classrooms;
}
