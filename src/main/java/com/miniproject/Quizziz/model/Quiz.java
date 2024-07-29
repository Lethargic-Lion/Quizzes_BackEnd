package com.miniproject.Quizziz.model;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quiz_id;
	private String title;
	private String description;
	private Long timer;
	
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime start_time;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime end_time;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date quiz_date;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Question.class)
	@JoinColumn(name = "quiz_id", referencedColumnName = "quiz_Id")
	@JsonManagedReference
	private List<Question> questions;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	@JsonBackReference
	@JsonIgnore
	private Teacher teacher;
	
	@ManyToOne
    @JoinColumn(name = "classroom_id")
	@JsonBackReference
	@JsonIgnore
    private Classroom classroom;
	
//	@OneToMany(cascade = CascadeType.ALL , targetEntity = Result.class)
//	@JoinColumn(name = "quiz_id" , referencedColumnName = "quiz_id")
//    private List<Result> results;
	

		
}
