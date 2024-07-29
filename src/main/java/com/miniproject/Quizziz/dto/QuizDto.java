package com.miniproject.Quizziz.dto;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Teacher;

import lombok.Data;

@Data
public class QuizDto {

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
	
	private List<Question> questions;
	private Teacher teacher;
    private Classroom classroom;
}
