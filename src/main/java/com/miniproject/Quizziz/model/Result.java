package com.miniproject.Quizziz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long result_id;
	private Integer score;
	private Integer quiz_id;
	private String name;
	private String student_Fname;
	private String student_Lname;
	private Integer student_id;
}
