package com.miniproject.Quizziz.dto;

import java.util.List;

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Quiz_Response;

import lombok.Data;

@Data
public class StudentDto {
	
	private int student_id;
	private String username;
	private String password;
	private String firstName;
    private String lastName;
    private String email;
	

    private List<Quiz_Response> responses;    
    private List<Classroom> classrooms;
}
