package com.miniproject.Quizziz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teacher_id;
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "teacher" , cascade =  CascadeType.ALL , targetEntity = Quiz.class)
	@JsonManagedReference
	@JsonIgnore
	private List<Quiz> quizzes;	
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, targetEntity = Classroom.class)
	@JsonManagedReference
    private List<Classroom> classrooms;

	
}
