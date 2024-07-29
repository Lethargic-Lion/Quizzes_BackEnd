package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.QuestionDto;
import com.miniproject.Quizziz.model.Question;

public class QuestionMapper {
	
	public Question dtoToEntity(QuestionDto dto) {
		Question entity = new Question();
		entity.setFill_up(dto.getFill_up());
		entity.setMcqOptions(dto.getMcqOptions());
		entity.setQuestionText(dto.getQuestionText());
//		entity.setQuestionId(dto.getQuestionId());
		entity.setQuestionType(dto.getQuestionType());
		entity.setQuiz(dto.getQuiz());
		entity.setResponses(dto.getResponses());
		return entity;
	}
	
	public QuestionDto entityToDto(Question entity) {
		QuestionDto dto = new QuestionDto();
		dto.setFill_up(entity.getFill_up());
		dto.setMcqOptions(entity.getMcqOptions());
		dto.setQuestionId(entity.getQuestionId());
		dto.setQuestionText(entity.getQuestionText());
		dto.setQuestionType(entity.getQuestionType());
		dto.setQuiz(entity.getQuiz());
		dto.setResponses(entity.getResponses());
		return dto;
	}
}
