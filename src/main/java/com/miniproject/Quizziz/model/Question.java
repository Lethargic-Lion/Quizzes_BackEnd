package com.miniproject.Quizziz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;
	private String questionText;

	public enum QuestionType {
		single_correct_mcq, multiple_correct_mcq, fill_ups;
	}

	@Enumerated(EnumType.STRING)
	private QuestionType questionType;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = MCQ_Option.class)
	@JoinColumn(name = "question_id", referencedColumnName = "questionId")
	private List<MCQ_Option> mcqOptions;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fillup_id")
	private Fill_Ups fill_up;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Quiz_Response.class)
	@JsonManagedReference
	@JsonIgnore
//	@JoinColumn(name = "question_id", referencedColumnName = "questionId")
	private List<Quiz_Response> responses;
	
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	@JsonBackReference
	private Quiz quiz;

}
