package com.miniproject.Quizziz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz_Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long response_id;
	private String selected_Option;
	private String fill_up_response;

	@ManyToOne
    @JoinColumn(name = "question_id")
	@JsonBackReference
	@JsonIgnore
    private Question question;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
	@JsonBackReference
	@JsonIgnore
    private Student student;
}
