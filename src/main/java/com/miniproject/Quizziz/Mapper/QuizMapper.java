package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.QuizDto;
import com.miniproject.Quizziz.model.Quiz;

public class QuizMapper {
	
	public Quiz dtoToEntity(QuizDto dto) {
		Quiz entity = new Quiz();
		entity.setTitle(dto.getTitle());
		entity.setTimer(dto.getTimer());
		entity.setTeacher(dto.getTeacher());
		entity.setStart_time(dto.getStart_time());
		entity.setEnd_time(dto.getEnd_time());
		entity.setDescription(dto.getDescription());
		entity.setQuiz_date(dto.getQuiz_date());
		entity.setQuestions(dto.getQuestions());
		entity.setClassroom(dto.getClassroom());
		return entity;
	}
	
	public QuizDto entityToDto(Quiz entity) {
		QuizDto dto = new QuizDto();
		dto.setTitle(entity.getTitle());
		dto.setTimer(entity.getTimer());
		dto.setTeacher(entity.getTeacher());
		dto.setStart_time(entity.getStart_time());
		dto.setEnd_time(entity.getEnd_time());
		dto.setDescription(entity.getDescription());
		dto.setQuiz_date(entity.getQuiz_date());
		dto.setQuestions(entity.getQuestions());
		dto.setClassroom(entity.getClassroom());
		dto.setQuiz_id(entity.getQuiz_id());
		return dto;
	}
}
