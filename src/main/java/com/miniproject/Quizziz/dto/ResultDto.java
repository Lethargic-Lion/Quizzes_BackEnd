package com.miniproject.Quizziz.dto;

import lombok.Data;

@Data
public class ResultDto {
	private Long result_id;
	private Integer score;
	private Integer quiz_id;
	private String name;
	private String student_Fname;
	private String student_Lname;
	private Integer student_id;
}
