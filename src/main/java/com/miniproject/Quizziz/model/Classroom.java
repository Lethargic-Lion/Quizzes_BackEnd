package com.miniproject.Quizziz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classroom_id;
	
	private String title;
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "teacher_id")
	@JsonBackReference
    private Teacher teacher;
	
	@ManyToMany(mappedBy = "classrooms", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<Student> students;
	
	@OneToMany(mappedBy = "classroom" , cascade =  CascadeType.ALL , targetEntity = Quiz.class)
	@JsonManagedReference
	@JsonIgnore
	private List<Quiz> quizzes;
}
